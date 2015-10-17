package cz.cvut.iss.service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author jakubchalupa
 * @since 08.10.15
 */
public final class ItemRepository {

    private static final Map<String, Long> ITEMS_MAPPING;

    static {
        ITEMS_MAPPING = new HashMap<>();

        ITEMS_MAPPING.put("fedora", 1l);
        ITEMS_MAPPING.put("rhel", 2l);
        ITEMS_MAPPING.put("ubuntu", 3l);
        ITEMS_MAPPING.put("aaa", 4l);
    }

    public boolean containsItemWithSku(String sku) {
        return ITEMS_MAPPING.containsKey(sku);
    }

    public Long getItemIdForSku(String sku) {
        return ITEMS_MAPPING.containsKey(sku) ? ITEMS_MAPPING.get(sku) : null;
    }

}
