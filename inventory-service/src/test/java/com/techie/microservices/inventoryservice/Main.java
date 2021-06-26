package com.techie.microservices.inventoryservice;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class Main {
    /**
     * Iterate through each line of input.
     */
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
        BufferedReader in = new BufferedReader(reader);
        String line;
        while ((line = in.readLine()) != null) {
            System.out.println(reversed(line));
        }
    }

    private static String reversed(String line) {
        String[] helper = line.split(";");
        int k = Integer.parseInt(helper[1]);
        if (k == 0) {
            return helper[0];
        }
        return reversedByK(helper[0].split(","), k);
    }

    private static String reversedByK(String[] nums, int k) {
        StringBuilder result = new StringBuilder();
        for (int i = k - 1; i < nums.length; i += k) {
            for (int j = i; i - j <= k - 1; j--) {
                result.append(',').append(nums[j]);
            }
        }

        for (int i = nums.length - nums.length % k; i < nums.length; i++) {
            result.append(',').append(nums[i]);
        }
        return result.substring(1);
    }
}
