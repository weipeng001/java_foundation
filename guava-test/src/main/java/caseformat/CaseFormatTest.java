package caseformat;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CaseFormatTest {
    @Test
    public void converterToTest(){
        /*返回一个Converter转换器，该转换器会将String按照源格式器转化为targetFormat格式*/
        Converter<String, String> camelConverter = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.UPPER_UNDERSCORE);
        String input = "input_camel";
        String result = camelConverter.convert(input);
        assertEquals("INPUT_CAMEL", result);
    }

    @Test
    public void toTest(){
        /*将str按照源caseFormat格式转化为目标format格式*/
        String result = CaseFormat.LOWER_HYPHEN.to(CaseFormat.LOWER_CAMEL,"foo-bar");
        assertEquals("fooBar", result);
    }
}
