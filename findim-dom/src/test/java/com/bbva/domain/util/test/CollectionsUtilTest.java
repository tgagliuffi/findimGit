package com.bbva.domain.util.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import com.bbva.findim.dom.util.CollectionsUtil;

public class CollectionsUtilTest {

	@Test
	public void stringToListComma() {
		String listStr = "hola,chau,bye";
		List<String> list = CollectionsUtil.stringToList(listStr, ",");
		assertTrue(list.contains("hola"));
		assertTrue(list.contains("chau"));
		assertTrue(list.contains("bye"));
		assertTrue(list.size() == 3);
	}

	@Test
	public void stringToListPipe() {
		String listStr = "hola|chau|bye";
		List<String> list = CollectionsUtil.stringToList(listStr, "|");
		assertTrue(list.contains("hola"));
		assertTrue(list.contains("chau"));
		assertTrue(list.contains("bye"));
		assertTrue(list.size() == 3);
	}

}
