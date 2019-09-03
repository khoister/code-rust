class Solution {
    private int start, maxLen;
    public String longestPalindrome(String s) {
        if (s.length() <= 1) return s;
        int n = s.length();
        for (int i=0; i<n; i++) {
            extend(s, i, i);
            extend(s, i, i+1);
        }
        return s.substring(start, start+maxLen);
    }
    void extend(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        if (maxLen < right - left - 1) {
            start = left + 1;
            maxLen = right - left - 1;
        } 
    }
}

class Solution {
    
    private int begin=0, maxLen=0;
    
    public String longestPalindrome(String s) {
        
        if(s.length() < 2) return s;
        
        for(int i=0; i< s.length(); i++){
            // if string is odd, middle element doesn't matter. We can also pass i-1, i+1
            extendPalindrome(s, i, i);
            extendPalindrome(s, i, i+1); //if String is even                   
        }        
        return s.substring(begin, begin+maxLen);
    }
    
    public void extendPalindrome(String s, int start, int end){
        
        //check and expand
        while(start >= 0 && end < s.length() && s.charAt(start)== s.charAt(end)){
            start--;
            end++;
        }
        
        //check for length and update maxLen
        if(maxLen < end - (start+1)){
            begin=start+1;
            maxLen= end- (start+1);
        }     
    }   
}
