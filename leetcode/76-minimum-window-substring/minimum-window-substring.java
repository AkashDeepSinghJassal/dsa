class Solution {
    public String minWindow(String s, String t) {
        /**
            example 
            "BJKQUUABCCBNAMUUCC"
         */
        String res = "";
        Map<Character, Integer> mapT = new HashMap<>();
        Map<Character, Integer> mapS = new HashMap<>();
        // set freq of str t
        for(char c : t.toCharArray()) {
            mapT.put(c, mapT.getOrDefault(c, 0) + 1);
        }

        /**
            find window size that contain all character of t
            check with the min length

            if freq of char of str s is < char of str t 
                continue
            if same 
                then validate both map
            else high 
                try to remove from left until any char of t is found
                    if same char is found keep removing until diff char is found
         */
        int l, r;
        l = r = 0;
        int min = Integer.MAX_VALUE;
        while (r < s.length()) {
            if(mapT.containsKey(s.charAt(r))) {
                mapS.put(s.charAt(r), mapS.getOrDefault(s.charAt(r), 0) + 1);
            } else {
                r++;
                continue;
            }
            // System.out.println(r + " val " + s.charAt(r)+ " at " + mapS);
            if (mapS.get(s.charAt(r)) >= mapT.get(s.charAt(r))) {
                while(true) {
                    if (!mapT.containsKey(s.charAt(l))) {
                        l++;
                    } else if(mapS.get(s.charAt(l)) > mapT.get(s.charAt(l))  ) { // only remove if freq of c in mapS > freq of c in map T
                        mapS.put(s.charAt(l), mapS.get(s.charAt(l)) - 1);
                        l++;
                    } else {
                        break;
                    }
                }
            }   
                if (validateMap(mapS, mapT)) {
                    // System.out.println(" in validate");
                    if(min > (r - l + 1)) {
                        min = r - l + 1;
                        res = s.substring(l, r + 1);
                    }

                }

            
            r++;
        }
        return res;
    }
    boolean validateMap(Map<Character, Integer> mapS, Map<Character, Integer> mapT) {
        if(mapS.size() != mapT.size()) {
            return false;
        }
        for(Map.Entry<Character, Integer> sEntry : mapS.entrySet()) {
            char sKey = sEntry.getKey();
            int sVal = sEntry.getValue();
            if(sVal < mapT.get(sKey)) {
                return false;
            }
        }
        return true;
    }
}