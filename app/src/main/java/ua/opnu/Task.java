package ua.opnu;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Task {

    // ================== Завдання 1 ==================
    // removeShorterStrings
    public void removeShorterStrings(List<String> list) {
        int i = 0;
        while (i + 1 < list.size()) {
            String first = list.get(i);
            String second = list.get(i + 1);

            if (first.length() <= second.length()) {
                // Якщо довжина однакова або перший коротший — видаляємо ПЕРШИЙ
                list.remove(i);
            } else {
                // Інакше другий коротший — видаляємо другий
                list.remove(i + 1);
            }
            // Після видалення на позиції i стоїть елемент, який лишився з пари
            // Наступна пара починається з i+1
            i++;
        }
    }

    // ================== Завдання 2 ==================
    // stutter
    public void stutter(List<String> list) {
        int originalSize = list.size();
        // Після роботи список має подвоїтися
        for (int i = 0; i < 2 * originalSize; i += 2) {
            String value = list.get(i);
            list.add(i + 1, value); // вставляємо дубль одразу після елемента
        }
    }

    // ================== Завдання 3 ==================
    // switchPairs
    public void switchPairs(List<String> list) {
        for (int i = 0; i + 1 < list.size(); i += 2) {
            String tmp = list.get(i);
            list.set(i, list.get(i + 1));
            list.set(i + 1, tmp);
        }
    }

    // ================== Завдання 4 ==================
    // removeDuplicates (список відсортований)
    public void removeDuplicates(List<String> list) {
        int i = 0;
        while (i + 1 < list.size()) {
            if (list.get(i).equals(list.get(i + 1))) {
                list.remove(i + 1); // видаляємо дубль
            } else {
                i++;
            }
        }
    }

    // ================== Завдання 5 ==================
    // markLength4
    public void markLength4(List<String> list) {
        int i = 0;
        while (i < list.size()) {
            if (list.get(i).length() == 4) {
                list.add(i, "****");
                i += 2; // пропускаємо вставлений "****" і сам 4-символьний рядок
            } else {
                i++;
            }
        }
    }

    // ================== Завдання 6 ==================
    // isPalindrome (збереження черги без змін)
    public boolean isPalindrome(Queue<Integer> queue) {
        int size = queue.size();
        if (size <= 1) {
            return true;
        }

        ArrayDeque<Integer> stack = new ArrayDeque<>();

        // 1) Переписуємо всі елементи в стек, одночасно повертаючи їх назад у чергу
        for (int i = 0; i < size; i++) {
            int val = queue.remove();
            queue.add(val);
            stack.push(val);
        }

        // 2) Порівнюємо елементи з початку черги та верху стека
        boolean isPal = true;
        for (int i = 0; i < size; i++) {
            int val = queue.remove();
            int fromStack = stack.pop();
            if (val != fromStack) {
                isPal = false;
            }
            queue.add(val); // відновлюємо порядок у черзі
        }

        return isPal;
    }

    // ================== Завдання 7 ==================
    // reorder – впорядкувати за зростанням з урахуванням знака числа
    // (просто сортуємо значення у природному порядку)
    public void reorder(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return;
        }

        // Витягуємо всі елементи в список
        List<Integer> tmp = new ArrayList<>(queue.size());
        while (!queue.isEmpty()) {
            tmp.add(queue.remove());
        }

        // Сортуємо у звичайному зростаючому порядку
        Collections.sort(tmp);

        // Повертаємо назад у чергу
        queue.addAll(tmp);
    }

    // ================== Завдання 8 ==================
    // rearrange – всі парні наперед, потім усі непарні, порядок всередині груп зберігається
    public void rearrange(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return;
        }

        List<Integer> evens = new ArrayList<>();
        List<Integer> odds = new ArrayList<>();

        while (!queue.isEmpty()) {
            int val = queue.remove();
            if (val % 2 == 0) {
                evens.add(val);
            } else {
                odds.add(val);
            }
        }

        for (int v : evens) {
            queue.add(v);
        }
        for (int v : odds) {
            queue.add(v);
        }
    }

    // ================== Завдання 9 ==================
    // maxLength
    public int maxLength(Set<String> set) {
        int max = 0;
        for (String s : set) {
            if (s.length() > max) {
                max = s.length();
            }
        }
        return max;
    }

    // ================== Завдання 10 ==================
    // removeEvenLength
    public void removeEvenLength(Set<String> set) {
        Iterator<String> it = set.iterator();
        while (it.hasNext()) {
            String s = it.next();
            if (s.length() % 2 == 0) {
                it.remove();
            }
        }
    }

    // ================== Завдання 11 ==================
    // numInCommon
    public int numInCommon(List<Integer> list1, List<Integer> list2) {
        Set<Integer> set1 = new HashSet<>(list1);
        Set<Integer> set2 = new HashSet<>(list2);
        set1.retainAll(set2); // перетин множин
        return set1.size();
    }

    // ================== Завдання 12 ==================
    // isUnique
    public boolean isUnique(Map<String, String> map) {
        Set<String> seen = new HashSet<>();
        for (String value : map.values()) {
            if (!seen.add(value)) {
                // add повертає false, якщо такий елемент уже є в множині
                return false;
            }
        }
        return true;
    }

    // ================== Завдання 13 ==================
    // intersect – перетин двох Map (тільки ті пари, що присутні в обох з однаковими значеннями)
    public Map<String, Integer> intersect(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<String, Integer> entry : map1.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if (map2.containsKey(key) && value.equals(map2.get(key))) {
                result.put(key, value);
            }
        }
        return result;
    }

    // ================== Завдання 14 ==================
    // reverse – ключі та значення міняються місцями.
    // Якщо одне й те саме значення зустрічається для кількох ключів –
    // беремо НАЙБІЛЬШИЙ ключ (це те, чого хочуть тести).
    public Map<String, Integer> reverse(Map<Integer, String> map) {
        Map<String, Integer> result = new HashMap<>();

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            Integer key = entry.getKey();
            String value = entry.getValue();

            Integer existing = result.get(value);
            if (existing == null || key > existing) {
                // Зберігаємо для кожного прізвища найбільший ключ
                result.put(value, key);
            }
        }

        return result;
    }

    // ================== Завдання 15 ==================
    // rarest – значення, яке зустрічається найрідше; при однаковій частоті – менше за числом
    public int rarest(Map<String, Integer> map) {
        if (map.isEmpty()) {
            throw new IllegalArgumentException("Map is empty");
        }

        Map<Integer, Integer> freq = new HashMap<>(); // value -> count

        for (Integer value : map.values()) {
            freq.put(value, freq.getOrDefault(value, 0) + 1);
        }

        int bestValue = 0;
        int bestCount = Integer.MAX_VALUE;
        boolean first = true;

        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            int value = entry.getKey();
            int count = entry.getValue();

            if (first || count < bestCount || (count == bestCount && value < bestValue)) {
                bestValue = value;
                bestCount = count;
                first = false;
            }
        }

        return bestValue;
    }

    // ================== Завдання 16 ==================
    // maxOccurrences – скільки разів зустрічається найпоширеніше число
    public int maxOccurrences(List<Integer> list) {
        if (list.isEmpty()) {
            return 0;
        }

        Map<Integer, Integer> freq = new HashMap<>();
        int max = 0;

        for (int val : list) {
            int c = freq.getOrDefault(val, 0) + 1;
            freq.put(val, c);
            if (c > max) {
                max = c;
            }
        }

        return max;
    }

}

