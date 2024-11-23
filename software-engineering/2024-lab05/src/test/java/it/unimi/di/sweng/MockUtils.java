package it.unimi.di.sweng;

import static org.mockito.Mockito.when;

import org.mockito.stubbing.Answer;

import java.util.Iterator;
import java.util.List;

public class MockUtils {
  @SafeVarargs
  public static <T> void whenIterated(Iterable<T> p, T... d) {
    when(p.iterator()).thenAnswer((Answer<Iterator<T>>) invocation -> List.of(d).iterator());
  }
}