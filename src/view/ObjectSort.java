package view;

import java.util.Comparator;

/**
 * Comparator which can be used to sort ObjectRenderers after the layer they are supposed to be 
 * drawn at.
 * 
 * @author Calleberg
 *
 */
@SuppressWarnings("rawtypes")
public class ObjectSort implements Comparator<ObjectRenderer>{

	@Override
	public int compare(ObjectRenderer o1, ObjectRenderer o2) {
		return o2.getLayer() - o1.getLayer();
	}
}
