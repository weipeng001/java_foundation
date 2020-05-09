package fprogram;

/**
 * @Author: zhuoli
 * @Date: 2018/6/11 21:09
 * @Description:
 */
public class FunctionInterfaceCore<T> {

    /**
     * @desc: 函数式接口作为方法的参数
     * @param: [ele1, ele2, functionInterfaceDemo]
     * @return: T
     * @author: zhuoli
     * @date: 2018/6/11 21:32
     */
    public T methodUseFunctionInterface(T ele1, T ele2, FunctionInterfaceDemo<T> functionInterfaceDemo) {
        return functionInterfaceDemo.calculation(ele1, ele2);
    }

    public static void main(String[] args) {
        /*lambda表达式隐式赋值给函数式接口对象*/
        FunctionInterfaceDemo<Integer> functionInterfaceDemo0 = (m, n) -> m + n;
        Integer addResult = functionInterfaceDemo0.calculation(4, 5);
        System.out.println("自定义函数式接口加操作:" + addResult);

        FunctionInterfaceDemo<Integer> functionInterfaceDemo1 = (m, n) -> m * n;
        Integer multiplyResult = functionInterfaceDemo1.calculation(4, 5);
        System.out.println("自定义函数式接口乘操作:" + multiplyResult);

        /*lambda表达式作为函数式接口参数*/
        FunctionInterfaceCore<Integer> functionInterfaceCore = new FunctionInterfaceCore<>();
        Integer functionParamResult = functionInterfaceCore.methodUseFunctionInterface(8, 9, (m, n) -> m + n);
        System.out.println("自定义函数式接口作为方法参数:" + functionParamResult);

    }
}
