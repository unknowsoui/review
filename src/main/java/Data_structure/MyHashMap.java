package Data_structure;

public class MyHashMap<K,V> implements Map<K,V>{
    private static class Entry<K,V>{
        private K key;
        private V value;
        private Entry<K,V> next;

        public Entry(K key,V value){
            this.key = key;
            this.value = value;
        }
    }

    private Entry<K,V>[] table = new Entry[16];
    private int size;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    @Override
    public V get(K key) {
        int hash = key.hashCode();
        hash = (hash >>> 16) ^ hash;
        int index = (table.length - 1) & hash;

        Entry<K,V> head = table[index];
        Entry<K,V> node = head;
        while(node != null){
            if(node.key.equals(key)){
                return node.value;
            }
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int hash = key.hashCode();
        hash = (hash >>> 16) & hash;
        int index = (table.length - 1) & hash;

        Entry<K,V> head = table[index];
        Entry<K,V> node = head;
        while(node != null){
            if(node.key.equals(key)){
                V temp = node.value;
                node.value = value;
                return temp;
            }
        }
        Entry<K,V> newnode = new Entry<>(key, value);
        if(head == null){
            table[index] = newnode;
        }else {
            Entry<K,V> last = head;
            while(last.next != null){
                last = last.next;
            }
            last.next = newnode;
        }
        size++;
        if(((double)size / table.length) >= LOAD_FACTOR_THRESHOLD){
            resize();
        }
        return null;
    }

    private void resize() {
        Entry<K,V>[] newtable = new Entry[table.length * 2];

        for(int i = 0;i < table.length;i++){
            Entry<K,V> node = table[i];
            while(node != null){
                int hash = node.key.hashCode();
                hash = (hash >>> 16) & hash;
                int index = (newtable.length - 1) & hash;

                Entry<K,V> temp = new Entry<>(node.key,node.value);
                temp.next = newtable[index];
                newtable[index] = temp;

                node = node.next;
            }
        }

        table = newtable;
    }
}
