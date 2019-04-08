package com.cdkj.coin.common;

import com.google.common.base.Preconditions;
import com.google.common.io.ByteStreams;
import com.google.common.primitives.Ints;
import com.google.common.primitives.Longs;
import com.google.protobuf.ByteString;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Sha256Hash  implements Serializable, Comparable<Sha256Hash> {
    public static final int LENGTH = 32;
    public static final Sha256Hash ZERO_HASH = wrap(new byte[32]);
    private final byte[] bytes;
    private long blockNum;

    private byte[] generateBlockId(long blockNum, Sha256Hash blockHash) {
        byte[] numBytes = Longs.toByteArray(blockNum);
        byte[] hash = blockHash.getBytes();
        System.arraycopy(numBytes, 0, hash, 0, 8);
        return hash;
    }

    private byte[] generateBlockId(long blockNum, byte[] blockHash) {
        byte[] numBytes = Longs.toByteArray(blockNum);
        System.arraycopy(numBytes, 0, blockHash, 0, 8);
        return blockHash;
    }

    public long getBlockNum() {
        return this.blockNum;
    }

    public Sha256Hash(long num, byte[] hash) {
        byte[] rawHashBytes = this.generateBlockId(num, hash);
        Preconditions.checkArgument(rawHashBytes.length == 32);
        this.bytes = rawHashBytes;
        this.blockNum = num;
    }

    public Sha256Hash(long num, Sha256Hash hash) {
        byte[] rawHashBytes = this.generateBlockId(num, hash);
        Preconditions.checkArgument(rawHashBytes.length == 32);
        this.bytes = rawHashBytes;
        this.blockNum = num;
    }

    /** @deprecated */
    @Deprecated
    public Sha256Hash(byte[] rawHashBytes) {
        Preconditions.checkArgument(rawHashBytes.length == 32);
        this.bytes = rawHashBytes;
    }

    public static Sha256Hash wrap(byte[] rawHashBytes) {
        return new Sha256Hash(rawHashBytes);
    }

    public static Sha256Hash wrap(ByteString rawHashByteString) {
        return wrap(rawHashByteString.toByteArray());
    }

    /** @deprecated */
    @Deprecated
    public static Sha256Hash create(byte[] contents) {
        return of(contents);
    }

    public static Sha256Hash of(byte[] contents) {
        return wrap(hash(contents));
    }

    public static Sha256Hash of(File file) throws IOException {
        FileInputStream in = new FileInputStream(file);
        Throwable var2 = null;

        Sha256Hash var3;
        try {
            var3 = of(ByteStreams.toByteArray(in));
        } catch (Throwable var12) {
            var2 = var12;
            throw var12;
        } finally {
            if (in != null) {
                if (var2 != null) {
                    try {
                        in.close();
                    } catch (Throwable var11) {
                        var2.addSuppressed(var11);
                    }
                } else {
                    in.close();
                }
            }

        }

        return var3;
    }

    /** @deprecated */
    @Deprecated
    public static Sha256Hash createDouble(byte[] contents) {
        return twiceOf(contents);
    }

    public static Sha256Hash twiceOf(byte[] contents) {
        return wrap(hashTwice(contents));
    }

    public static MessageDigest newDigest() {
        try {
            return MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException var1) {
            throw new RuntimeException(var1);
        }
    }

    public static byte[] hash(byte[] input) {
        return hash(input, 0, input.length);
    }

    public static byte[] hash(byte[] input, int offset, int length) {
        MessageDigest digest = newDigest();
        digest.update(input, offset, length);
        return digest.digest();
    }

    public static byte[] hashTwice(byte[] input) {
        return hashTwice(input, 0, input.length);
    }

    public static byte[] hashTwice(byte[] input, int offset, int length) {
        MessageDigest digest = newDigest();
        digest.update(input, offset, length);
        return digest.digest(digest.digest());
    }

    public static byte[] hashTwice(byte[] input1, int offset1, int length1, byte[] input2, int offset2, int length2) {
        MessageDigest digest = newDigest();
        digest.update(input1, offset1, length1);
        digest.update(input2, offset2, length2);
        return digest.digest(digest.digest());
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else {
            return o != null && o instanceof Sha256Hash ? Arrays.equals(this.bytes, ((Sha256Hash)o).bytes) : false;
        }
    }

    public String toString() {
        return ByteArray.toHexString(this.bytes);
    }

    public int hashCode() {
        return Ints.fromBytes(this.bytes[28], this.bytes[29], this.bytes[30], this.bytes[31]);
    }

    public BigInteger toBigInteger() {
        return new BigInteger(1, this.bytes);
    }

    public byte[] getBytes() {
        return this.bytes;
    }

    public ByteString getByteString() {
        return ByteString.copyFrom(this.bytes);
    }

    public int compareTo(Sha256Hash other) {
        for(int i = 31; i >= 0; --i) {
            int thisByte = this.bytes[i] & 255;
            int otherByte = other.bytes[i] & 255;
            if (thisByte > otherByte) {
                return 1;
            }

            if (thisByte < otherByte) {
                return -1;
            }
        }

        return 0;
    }
}
