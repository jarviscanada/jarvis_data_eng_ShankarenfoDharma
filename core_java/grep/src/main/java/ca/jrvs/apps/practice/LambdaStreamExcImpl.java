package ca.jrvs.apps.practice;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class LambdaStreamExcImpl implements LambdaStreamExc{

    public static void main(String[] args) {
        LambdaStreamExcImpl lSEI = new LambdaStreamExcImpl();
        lSEI.printOdd(lSEI.createIntStream(0, 20), lSEI.getLambdaPrinter("odd:", "!"));
    }

    @Override
    public Stream<String> createStrStream(String... strings) {
        return Arrays.stream(strings);
    }

    @Override
    public Stream<String> toUpperCase(String... strings) {
        return createStrStream(strings).map(string -> string.toUpperCase());
    }

    @Override
    public Stream<String> filter(Stream<String> stringStream, String pattern) {
        return stringStream.filter(string -> !string.contains(pattern));
    }

    @Override
    public IntStream createIntStream(int[] arr) {
        return Arrays.stream(arr);
    }

    @Override
    public <E> List<E> toList(Stream<E> stream) {
        return stream.collect(Collectors.toList());
    }

    @Override
    public List<Integer> toList(IntStream intStream) {
        return intStream.boxed().collect(Collectors.toList());
    }

    @Override
    public IntStream createIntStream(int start, int end) {
        return IntStream.range(start,end);
    }

    @Override
    public DoubleStream squareRootIntStream(IntStream intStream) {
        return intStream.mapToDouble(intElement -> intElement*intElement);
    }

    @Override
    public IntStream getOdd(IntStream intStream) {
        return intStream.filter(intElement -> (intElement % 2 != 0) );
    }

    @Override
    public Consumer<String> getLambdaPrinter(String prefix, String suffix) {
        Consumer<String> lambda = (infix) -> {System.out.println(prefix + infix + suffix);};
        return lambda;
    }

    @Override
    public void printMessages(String[] messages, Consumer<String> printer) {
        for (String message:messages) {
            printer.accept(message);
        }
    }

    @Override
    public void printOdd(IntStream intStream, Consumer<String> printer) {
        IntStream oddStream = getOdd(intStream);
        oddStream.forEach(intElement -> printer.accept(""+intElement));
    }

    @Override
    public Stream<Integer> flatNestedInt(Stream<List<Integer>> ints) {
        //METHOD 1: flatmap listElement->each int element squared
        return ints.flatMap( intListElement -> intListElement.stream().map(intElement -> intElement*intElement) );
    }
}
