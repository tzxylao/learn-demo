package com.tzxylao.java8;

import java.io.InputStream;

public class GenerateUnityAAR {
    public static void main(String[] args) {

        String path = "mspaint";

        Runtime run = Runtime.getRuntime();

        try {

            Process process = run.exec("cmd.exe /k start " + path);

            InputStream in = process.getInputStream();

            while (in.read() != -1) {

                System.out.println(in.read());

            }

            in.close();

            process.waitFor();

        } catch (Exception e) {

            e.printStackTrace();

        }

    }

}