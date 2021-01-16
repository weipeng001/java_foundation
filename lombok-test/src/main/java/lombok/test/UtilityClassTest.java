package lombok.test;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UtilityClassTest {
    public String name(){
      return "UtilityClassTest";
    }
}
