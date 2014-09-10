package rubedo.common;

import java.lang.reflect.Constructor;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import rubedo.items.MultiItem;
import net.minecraftforge.common.Configuration;

public class ContentMultiItem<T extends MultiItem> implements IContent {

	private Map<Class<? extends T>, T> multiItems = new LinkedHashMap<Class<? extends T>, T>();

	public void setItems(List<Class<? extends T>> classes) {
		for (Class<? extends T> clazz : classes)
			multiItems.put(clazz, null);
	}

	public T getItem(Class<? extends T> item) {
		return multiItems.get(item);
	}

	@Override
	public void config(Configuration config) {
		for (Class<? extends T> clazz : multiItems.keySet()) {
			Config.initId(clazz.getSimpleName());
		}
	}

	@Override
	public void register() {
		try {
			for (Class<? extends T> clazz : multiItems.keySet()) {
				Constructor<? extends T> constructor = clazz.getConstructor(int.class);
				T instance = constructor.newInstance(Config.getId(clazz
						.getSimpleName()));

				multiItems.put(clazz, instance);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}