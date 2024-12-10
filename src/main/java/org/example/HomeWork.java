package org.example;


import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class HomeWork {

    /**
     * <h1>Задание 1.</h1>
     * Решить задачу Step из файла contest6_tasks.pdf
     */
    @SneakyThrows
    public void stepDanceValue(InputStream in, OutputStream out) {
        // На всякий случай проверим входные параметры
        if (in == null) {
            throw new IllegalArgumentException("Входящий поток не может быть null");
        }
        if (out == null) {
            throw new IllegalArgumentException("Выходящий поток не может быть null");
        }
        AVLTree tree = new AVLTree();
        BufferedReader br = new BufferedReader(new InputStreamReader(in));

        List<Integer> ints = br.lines().
                flatMap(str -> Arrays.stream(str.split(" ")))
                .map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> result = new ArrayList<>(ints.get(2));
        IntStream.range(1, ints.get(0) + 1).forEach(tree::add);
        ints.stream().skip(2).forEach(
                i -> {
                    tree.change(i);
                    result.add(tree.uniq());
                }
        );

        out.write(result.stream()
                .map(String::valueOf)
                .collect(Collectors.joining("\n"))
                .getBytes());
    }
}
