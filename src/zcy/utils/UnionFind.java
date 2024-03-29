package zcy.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Stack;

/**
 * @ClassName UnionFind
 * @Description TODO
 * @Author XiaoPengCheng
 * @Date 2022-9-19 17:06
 * @Version 1.0
 */
public class UnionFind {

    public static class Element<V> {
        public V v;

        public Element(V v) {
            this.v = v;
        }
    }

    public static class UnionFindSet<V> {
        public HashMap<V, Element<V>> elementMap;
        // key 某个元素  value 该元素的父
        public HashMap<Element<V>, Element<V>> fatherMap;
        // key 某个元素的代表元素  value 该集合的大小
        public HashMap<Element<V>, Integer> sizeMap;

        public UnionFindSet(List<V> list){
            elementMap = new HashMap<>();
            fatherMap = new HashMap<>();
            sizeMap = new HashMap<>();
            for (V value : list) {
                Element<V> element = new Element<>(value);
                elementMap.put(value, element);
                fatherMap.put(element, element);
                sizeMap.put(element, 1);
            }
        }

        public boolean isSameSet(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                return findHead(elementMap.get(a)) == findHead(elementMap.get(b));
            }
            return false;
        }

        public void union(V a, V b) {
            if (elementMap.containsKey(a) && elementMap.containsKey(b)) {
                Element<V> aF = findHead(elementMap.get(a));
                Element<V> bF = findHead(elementMap.get(b));
                if (aF != bF) {
                    Element<V> big = sizeMap.get(aF) >= sizeMap.get(bF) ? aF : bF;
                    Element<V> small = big == aF ? bF : aF;
                    fatherMap.put(small, big);
                    sizeMap.put(big, sizeMap.get(aF) + sizeMap.get(bF));
                    sizeMap.remove(small);
                }
            }
        }

        // 给一个元素，往上一直找，把代表元素返回
        private Element<V> findHead(Element<V> element) {
            Stack<Element<V>> path = new Stack<>();
            while (element != fatherMap.get(element)) {
                path.push(element);
                element = fatherMap.get(element);
            }
            while (!path.isEmpty()) {
                fatherMap.put(path.pop(), element);
            }
            return element;
        }
    }

}
