package com.in28minutes.mockito.mockito_demo.list;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ListTest {

	@Test
	void simpleTest() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(3);

		assertEquals(3, listMock.size());
		assertEquals(3, listMock.size());
		assertEquals(3, listMock.size());
	}

	@Test
	void multipleReturns() {
		List listMock = mock(List.class);
		when(listMock.size()).thenReturn(1).thenReturn(2).thenReturn(5);

		assertEquals(1, listMock.size());
		assertEquals(2, listMock.size());
		assertEquals(5, listMock.size());
		assertEquals(5, listMock.size());
		assertEquals(5, listMock.size());
	}

	@Test
	void specificParameters() {
		List listMock = mock(List.class);
		when(listMock.get(0)).thenReturn("SomeString");

		assertEquals("SomeString", listMock.get(0));
		assertEquals(null, listMock.get(1));
	}

	@Test
	void genericParameters() {
		List listMock = mock(List.class);
		when(listMock.get(Mockito.anyInt())).thenReturn("SomeOtherString");

		assertEquals("SomeOtherString", listMock.get(0));
		assertEquals("SomeOtherString", listMock.get(231));
		assertEquals("SomeOtherString", listMock.get(5444));
	}
}