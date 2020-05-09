package charmatcher;

import com.google.common.base.CharMatcher;
import com.google.common.base.Predicate;
import org.junit.Test;

import static org.junit.Assert.*;

public class CharMatcherTest {
    @Test
    public void retainFromTest() {
        String input = "H*el.lo,}12";
        //匹配字母和数字
        CharMatcher matcher = CharMatcher.forPredicate(Character::isLetterOrDigit);
        String result = matcher.retainFrom(input);

        assertEquals("Hello12", result);
    }

    @Test
    public void andTest() {
        /*返回两个Matcher执行逻辑与操作的Matcher*/
        String input = "H*el.lo,}12";

        //匹配字母
        CharMatcher matcher0 = CharMatcher.forPredicate(Character::isLetter);
        //匹配小写的字母
        CharMatcher matcher1 = CharMatcher.forPredicate(Character::isLowerCase);

        String result = matcher0.and(matcher1).retainFrom(input);
        assertEquals("ello", result);
    }

    @Test
    public void anyTest() {
        /*匹配任意字符*/
        String input = "H*el.lo,}12";
        CharMatcher matcher = CharMatcher.any();

        String result = matcher.retainFrom(input);
        assertEquals("H*el.lo,}12", result);
    }

    @Test
    public void anyOfTest() {
        /*匹配在CharSequence内的任意一个字符*/
        String input = "H*el.lo,}12";
        CharMatcher matcher = CharMatcher.anyOf("Hel");

        String result = matcher.removeFrom(input);
        assertEquals("*.o,}12", result);
    }

    @Test
    public void asciiTest() {
        /*匹配Ascii*/
        String input = "あH*el.lo,}12";
        CharMatcher matcher = CharMatcher.ascii();

        String result = matcher.retainFrom(input);
        assertEquals("H*el.lo,}12", result);
    }

    @Test
    public void breakingWhitespaceTest() {
        /*匹配所有可换行的空白字符，(不包括非换行空白字符,例如"\u00a0")*/
        String input = " this is test ";
        CharMatcher matcher = CharMatcher.breakingWhitespace();

        String result = matcher.removeFrom(input);
        assertEquals("thisistest", result);
    }

    @Test
    public void collapseTest() {
        /*将charMatcher连续被匹配到的字符用一个replacement替换*/
        String input = "       hel    lo      ";

        String result = CharMatcher.is(' ').collapseFrom(input, '-');
        assertEquals("-hel-lo-", result);

        /*先进性Trim操作(讲charSequence头和尾匹配到的连续字符去除)，再进行collapseFrom操作*/
        result = CharMatcher.is(' ').trimAndCollapseFrom(input, '-');
        assertEquals("hel-lo", result);
    }

    @Test
    public void countInTest() {
        /*获取charMatcher匹配到字符的个数*/
        String input = "H*el.lo,}12";
        CharMatcher matcher = CharMatcher.forPredicate(Character::isLetterOrDigit);
        int count = matcher.countIn(input);
        assertEquals(7, count);
    }

    @Test
    public void forPredicateTest() {
        /*通过predicate初始化charMatcher*/
        CharMatcher matcher = CharMatcher.forPredicate(Character::isLetterOrDigit);
//
//        Predicate<Character> isLetterOrDigit = new Predicate<Character>() {
//            @Override
//            public boolean apply(@Nullable Character character) {
//                return Character.isLetterOrDigit(character);
//            }
//        };
        //完整写法 如上
        Predicate<Character> isLetterOrDigit = character -> Character.isLetterOrDigit(character);
        CharMatcher matcher1 = CharMatcher.forPredicate(isLetterOrDigit);
    }

    @Test
    public void indexInTest() {
        /*获取charMatcher匹配到第一个字符的index*/
        String input = "**el.lo,}12";
        CharMatcher matcher = CharMatcher.forPredicate(Character::isLetterOrDigit);
        int index = matcher.indexIn(input);
        assertEquals(2, index);

        index = matcher.indexIn(input, 4);
        assertEquals(5, index);
    }

    @Test
    public void inRangeTest() {
        /*初始化范围匹配器*/
        String input = "a, c, z, 1, 2";

        int result = CharMatcher.inRange('a', 'h').countIn(input);
        assertEquals(2, result);

    }

    @Test
    public void isTest(){
        /*通过char初始化charMatcher,匹配单个字符*/
        String input = "a, c, z, 1, 2";
        int result = CharMatcher.is(',').countIn(input);
        assertEquals(4, result);
    }

    @Test
    public void isNotTest(){
        /*匹配参数之外的所有字符,与is相反*/
        String input = "a, c, z, 1, 2";
        String result = CharMatcher.isNot(',').removeFrom(input);
        assertEquals(",,,,", result);
    }

    @Test
    public void javaIsoControlTest(){
        /*匹配java转义字符*/
        String input = "ab\tcd\nef\bg";
        String result = CharMatcher.javaIsoControl().removeFrom(input);
        assertEquals("abcdefg", result);
    }

    @Test
    public void lastIndexInTest(){
        /*获取charMatcher匹配到最后一个字符的index*/
        String input = "**e,l.lo,}12";
        CharMatcher matcher = CharMatcher.is(',');
        int index = matcher.lastIndexIn(input);
        assertEquals(8, index);
    }

    @Test
    public void matchesAllOfTest(){
        /*判断CharSequence每一个字符是不是都已被charMatcher匹配*/
        String input = "**e,l.lo,}12";
        CharMatcher matcher = CharMatcher.is(',');
        assertFalse(matcher.matchesAllOf(input));
    }

    @Test
    public void matchesAnyOfTest(){
        /*判断CharSequence是否存在字符被charMatcher匹配*/
        String input = "**e,l.lo,}12";
        CharMatcher matcher = CharMatcher.is(',');
        assertTrue(matcher.matchesAnyOf(input));
    }

    @Test
    public void matchesNoneOfTest(){
        /*判断CharSequence是否每一个字符都没有被charMatcher匹配*/
        String input = "**e,l.lo,}12";
        CharMatcher matcher = CharMatcher.is('?');
        assertTrue(matcher.matchesNoneOf(input));
    }

    @Test
    public void negateTest(){
        /*返回与当前CharMatcher相反的CharMatcher*/
        String input = "あH*el.lo,}12";
        /*charMatcher为非ascii*/
        CharMatcher matcher = CharMatcher.ascii().negate();

        String result = matcher.retainFrom(input);
        assertEquals("あ", result);
    }

    @Test
    public void noneTest(){
        /*不匹配任何字符,与any()相反*/
        String input = "H*el.lo,}12";
        CharMatcher matcher = CharMatcher.none();

        String result = matcher.retainFrom(input);
        assertEquals("", result);
    }

    @Test
    public void noneOfTest(){
        /*不匹配CharSequence内的任意一个字符,与anyOf()相反*/
        String input = "H*el.lo,}12";
        CharMatcher matcher = CharMatcher.noneOf("Hel");

        String result = matcher.removeFrom(input);
        assertEquals("Hell", result);
    }

    @Test
    public void orTest(){
        /*返回两个Matcher执行逻辑或操作的Matcher*/
        String input = "H*el.lo,}12";

        CharMatcher matcher0 = CharMatcher.forPredicate(Character::isLetter);
        CharMatcher matcher1 = CharMatcher.forPredicate(Character::isDigit);

        String result = matcher0.or(matcher1).retainFrom(input);
        assertEquals("Hello12", result);
    }

    @Test
    public void trimFromTest(){
        String input = "---hello,,,";

        /*删除首部匹配到的字符*/
        String result = CharMatcher.is('-').trimLeadingFrom(input);
        assertEquals("hello,,,", result);

        /*删除尾部匹配到的字符*/
        result = CharMatcher.is(',').trimTrailingFrom(input);
        assertEquals("---hello", result);

        /*删除首尾匹配到的字符*/
        result = CharMatcher.anyOf("-,").trimFrom(input);
        assertEquals("hello", result);
    }

    @Test
    public void whitespaceTest(){
        /*匹配所有空白字符*/
        String input = "       hel    lo      ";

        String result = CharMatcher.whitespace().collapseFrom(input, '-');
        assertEquals("-hel-lo-", result);

    }
}
