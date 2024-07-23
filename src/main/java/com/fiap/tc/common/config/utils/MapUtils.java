package com.fiap.tc.common.config.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MapUtils {

	public static <V, K> Map<K, V> difference(Map<K, V> leftMap, Map<K, V> rightMap) {
		Map<K, V> difference = new HashMap<>();

		leftMap.forEach((k, v) -> {
			V value = rightMap.get(k);
			if (Objects.isNull(value)) {
				difference.put(k, v);
			}
		});
		return difference;
	}
}
