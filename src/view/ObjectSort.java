package view;

import java.util.Comparator;

@SuppressWarnings("rawtypes")
public class ObjectSort implements Comparator<ObjectRenderer>{

	@Override
	public int compare(ObjectRenderer o1, ObjectRenderer o2) {
		return o1.getLayer() - o2.getLayer();
	}
}
