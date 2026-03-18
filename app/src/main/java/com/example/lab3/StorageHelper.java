package com.example.lab3;

import android.content.Context;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public final class StorageHelper {

    private StorageHelper() {}

    private static final String FILE_NAME = "results.txt";

    public static boolean appendLine(Context context, String line) {
        try (OutputStreamWriter writer = new OutputStreamWriter(
                context.openFileOutput(FILE_NAME, Context.MODE_APPEND)
        )) {
            writer.write(line);
            writer.write("\n");
            writer.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String readAll(Context context) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(context.openFileInput(FILE_NAME))
        )) {
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s).append('\n');
            }
        } catch (FileNotFoundException e) {
            return "";
        } catch (Exception e) {
            return "";
        }
        return sb.toString().trim();
    }

    public static boolean clear(Context context) {
        try (OutputStreamWriter writer = new OutputStreamWriter(
                context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)
        )) {
            writer.write("");
            writer.flush();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}