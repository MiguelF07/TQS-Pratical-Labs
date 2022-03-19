package com.tqs.connection;

import java.io.IOException;

public interface ISimpleHttpClient {
    public String doHttpGet(String s) throws IOException;
}
