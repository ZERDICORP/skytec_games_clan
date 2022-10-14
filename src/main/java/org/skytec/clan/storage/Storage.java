package org.skytec.clan.storage;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.skytec.clan.object.GameObject;

/**
 * @author zerdicorp
 * @project skytec_games_clan
 * @created 10/11/22 - 9:12 AM
 */

public abstract class Storage<K, V extends GameObject<K>> {
  protected Map<K, V> map = new HashMap<>();

  public List<V> getAll() {
    return (List<V>) map.values();
  }

  public Optional<V> findById(K key) {
    return Optional.ofNullable(map.get(key));
  }

  public void save(V value) {
    map.put(value.id(), value);
  }
}
