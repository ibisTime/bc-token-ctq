package com.cdkj.coin.Tron;

import com.alibaba.fastjson.annotation.JSONField;

public class Block {
    private String blockID;

    @JSONField(name = "block_header")
    private BlockHeader blockHeader;

    public class BlockHeader {

        @JSONField(name = "witness_signature")
        private String witnessSignature;

        @JSONField(name = "raw_data")
        private rawData rawData;

        public class rawData {
            private Integer number;

            private String txTrieRoot;

            @JSONField(name = "witness_address")
            private String witnessAddress;

            private String parentHash;

            private Integer version;

            private Long timestamp;

            public Integer getNumber() {
                return number;
            }

            public void setNumber(Integer number) {
                this.number = number;
            }

            public String getTxTrieRoot() {
                return txTrieRoot;
            }

            public void setTxTrieRoot(String txTrieRoot) {
                this.txTrieRoot = txTrieRoot;
            }

            public String getWitnessAddress() {
                return witnessAddress;
            }

            public void setWitnessAddress(String witnessAddress) {
                this.witnessAddress = witnessAddress;
            }

            public String getParentHash() {
                return parentHash;
            }

            public void setParentHash(String parentHash) {
                this.parentHash = parentHash;
            }

            public Integer getVersion() {
                return version;
            }

            public void setVersion(Integer version) {
                this.version = version;
            }

            public Long getTimestamp() {
                return timestamp;
            }

            public void setTimestamp(Long timestamp) {
                this.timestamp = timestamp;
            }
        }

        public String getWitnessSignature() {
            return witnessSignature;
        }

        public void setWitnessSignature(String witnessSignature) {
            this.witnessSignature = witnessSignature;
        }

        public BlockHeader.rawData getRawData() {
            return rawData;
        }

        public void setRawData(BlockHeader.rawData rawData) {
            this.rawData = rawData;
        }
    }

    public String getBlockID() {
        return blockID;
    }

    public void setBlockID(String blockID) {
        this.blockID = blockID;
    }

    public BlockHeader getBlockHeader() {
        return blockHeader;
    }

    public void setBlockHeader(BlockHeader blockHeader) {
        this.blockHeader = blockHeader;
    }
}
