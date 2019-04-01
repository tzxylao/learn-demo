package com.tzxylao.design.frequently.delegate;

public enum ExecuteTypeEnum{
        A("A"),B("B");
        private String name;

        ExecuteTypeEnum(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }