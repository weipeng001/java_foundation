package lombok.test;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.ExtensionMethod;

@Data
//@Accessors(prefix ={"a","b","p"}, chain = true)
@Accessors(prefix ={"a","b","p"}, fluent = true,chain = true)
@NoArgsConstructor
class AccessorsTest {
	private Long pId;
	private String pName;
	private String aTestA;
	private String bTestB;
}