package com.cdkj.coin.Tron;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

public class TrxTx {

    private List<Transaction> transactions;

    public class Transaction {

        private List<Ret> ret;

        private String txID;

        @JSONField(name = "raw_data")
        private rawData rawData;

        @JSONField(name = "raw_data_hex")
        private String rawDataHex;

        public class Ret{
            private String contractRet;

            public String getContractRet() {
                return contractRet;
            }

            public void setContractRet(String contractRet) {
                this.contractRet = contractRet;
            }
        }

        public class rawData {
            private List<Contract> contract;


            public class Contract {
                private Parameter parameter;

                private String type;

                public class Parameter {
                    private Value value;

                    @JSONField(name = "type_url")
                    private String typeUrl;

                    public class Value {
                        private long amount;

                        @JSONField(name = "owner_address")
                        private String ownerAddress;

                        @JSONField(name = "to_address")
                        private String toAddress;

                        public long getAmount() {
                            return amount;
                        }

                        public void setAmount(long amount) {
                            this.amount = amount;
                        }

                        public String getOwnerAddress() {
                            return ownerAddress;
                        }

                        public void setOwnerAddress(String ownerAddress) {
                            this.ownerAddress = ownerAddress;
                        }

                        public String getToAddress() {
                            return toAddress;
                        }

                        public void setToAddress(String toAddress) {
                            this.toAddress = toAddress;
                        }
                    }

                    public Value getValue() {
                        return value;
                    }

                    public void setValue(Value value) {
                        this.value = value;
                    }

                    public String getTypeUrl() {
                        return typeUrl;
                    }

                    public void setTypeUrl(String typeUrl) {
                        this.typeUrl = typeUrl;
                    }
                }

                public Parameter getParameter() {
                    return parameter;
                }

                public void setParameter(Parameter parameter) {
                    this.parameter = parameter;
                }

                public String getType() {
                    return type;
                }

                public void setType(String type) {
                    this.type = type;
                }
            }

            public List<Contract> getContract() {
                return contract;
            }

            public void setContract(List<Contract> contract) {
                this.contract = contract;
            }
        }

        public String getTxID() {
            return txID;
        }

        public void setTxID(String txID) {
            this.txID = txID;
        }

        public Transaction.rawData getRawData() {
            return rawData;
        }

        public void setRawData(Transaction.rawData rawData) {
            this.rawData = rawData;
        }

        public String getRawDataHex() {
            return rawDataHex;
        }

        public void setRawDataHex(String rawDataHex) {
            this.rawDataHex = rawDataHex;
        }

        public List<Ret> getRet() {
            return ret;
        }

        public void setRet(List<Ret> ret) {
            this.ret = ret;
        }
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
