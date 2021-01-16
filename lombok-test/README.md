Lombok简介

> Lombok 是一种 Java实用工具，可用来帮助开发人员消除Java的冗长，尤其是对于简单的Java对象(POJO), 
> 它通过注释实现这一目的。一个标准的Java bean 一般具有若干属性，每个属性具有getter()和setter()方法，
> Lombok中也用到了注解，但是它并没有用到反射，而是通过一些奇技淫巧，在代码编译时期动态将注解替换为具体的代码。
> 所以JVM实际运行的代码，和我们手动编写的包含了各种工具方法的类相同。

* 要在 project 中使用 lombok，除了要在 maven 中加入 lombok dependency，还要安装 lombok 插件 

Lombok注解
* val: final 像动态语言一样，声明一个fianl的变量。
* var: 同JDK10
* @Data：注解在类上，将类提供的所有属性都添加get、set方法，并添加、equals、canEquals、hashCode、toString方法
* @Setter：注解在类上，为所有属性添加set方法、注解在属性上为该属性提供set方法
* @Getter：注解在类上，为所有的属性添加get方法、注解在属性上为该属性提供get方法
* @NonNull：在参数中使用时，如果调用时传了null值，就会抛出空指针异常
* @Synchronized 用于方法，可以锁定指定的对象，如果不指定，则默认创建一个对象锁定
* @Log作用于类，创建一个log属性
* @Builder：使用builder模式创建对象
* @NoArgsConstructor：创建一个无参构造函数
* @AllArgsConstructor：创建一个全参构造函数
* @ToString：创建一个toString方法
* @Accessors(chain = true)使用链式设置属性，set方法返回的是this对象。
* @RequiredArgsConstructor：创建对象, 例: 在class上添加@RequiredArgsConstructor(staticName = "of")会创建生成一个静态方法
* @UtilityClass:工具类
* @ExtensionMethod:设置父类
* @FieldDefaults：设置属性的使用范围，如private、public等，也可以设置属性是否被final修饰。
* @Cleanup: 关闭流、连接点。
* @EqualsAndHashCode：重写equals和hashcode方法。
* @toString：创建toString方法。
* @Cleanup: 用于流等可以不需要关闭使用流对象.

