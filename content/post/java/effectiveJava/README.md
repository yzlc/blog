---
title: "Effective Java"
date: 2019-04-19T21:37:58+05:30
description: ""
tags: [Java]
---

## 1. 考虑使用静态工厂方法替代构造方法
```
public static Boolean valueOf(boolean b) {
  return b ? Boolean.TRUE : Boolean.FALSE;
}
```
### 优点
- 有名字(BigInteger.probablePrime)
- 不用每次调用都创建新对象(Boolean.valueOf(boolean))
- 可以返回子类型的对象(Collections)
- 返回对象的类可以根据输入参数的不同而不同(EnumSet.noneOf())
- 编写包含该方法的类时，返回的对象的类不需要存在(ServiceLoader)
### 缺点
- 没有公共或受保护构造方法的类不能被子类化(Collections)
- 程序员很难找到它们
## 2. 当构造方法参数过多时使用 builder 模式
```
// Builder Pattern
public class NutritionFacts {
    private final int calories;
    public static class Builder {
        private int calories = 0;
        public Builder() {}
        public Builder calories(int val) {
            calories = val;
            return this;
        }
        public NutritionFacts build() {
            return new NutritionFacts(this);
        }
    }
    private NutritionFacts(Builder builder) {
        calories = builder.calories;
    }
}
```
## 3. 使用私有构造方法或枚举类实现 Singleton 属性
```
// Singleton with static factory
// 1.防范AccessibleObject.setAccessible：修改构造函数
// 2.implements Serializable：声明实例属性transient，并提供readResolve方法
public class Elvis {
  private static final Elvis INSTANCE = new Elvis();
  private Elvis() { ... }
  public static Elvis getInstance() { return INSTANCE; }
  public void leaveTheBuilding() { ... }
}

// Enum singleton - the preferred approach
// 不适用：单例必须继承 Enum 以外的父类
public enum Elvis {
  INSTANCE;
  public void leaveTheBuilding() { ... }
}
```
## 4. 使用私有构造方法执行非实例化
- Java8，可以放接口中
## 5. 依赖注入优于硬连接资源（hardwiring resources）
```
// Dependency injection provides flexibility and testability
public class SpellChecker {
  private final Lexicon dictionary;
  public SpellChecker(Lexicon dictionary) {
    this.dictionary = Objects.requireNonNull(dictionary);
  }
}
```
## 6. 避免创建不必要的对象
```
// Performance can be greatly improved!
static boolean isRomanNumeral(String s) {
  return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
      + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
}

// Reusing expensive object for improved performance
public class RomanNumerals {
  private static final Pattern ROMAN = Pattern.compile(
      "^(?=.)M*(C[MD]|D?C{0,3})"
      + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
  static boolean isRomanNumeral(String s) {
    return ROMAN.matcher(s).matches();
 }
}
```
- 优先使用基本类型而不是装箱的基本类型，也要注意无意识的自动装箱
## 7. 消除过期的对象引用
- 类自己管理内存时，应该警惕内存泄漏问题
- 缓存
- 监听器和其他回调
## 8. 避免使用 Finalizer 和 Cleaner 机制
## 9. 使用 try-with-resources 语句替代 try-finally 语句
## 10. 重写 equals 方法时遵守通用约定
### 约定
- 自反性：x.equals(x) == true
- 对称性：x.equals(y) == true，则y.equals(x) == true 反例：equalsIgnoreCase
- 传递性：x.equals(y) == true && y.equals(z) == true，则x.equals(z) == true 例如：java.sql.Timestamp extends java.util.Date
- 一致性：多次调用结果相同（不要依赖不可靠资源） 反例：java.net.URL
- 非空性：必须做if(!(o instanceof MyType))所以不需要if(o == null)
### 写法
1. if(o == MyType)
2. if(!(o instanceof MyType))
3. 非float或double的基本类型用==比较，引用类型递归调用equals
    - 因为存在Float(Double).NaN、-0.0f，使用Float(Double).compare(float,float)
    - 不使用Float(Double).equals，自动装箱性能差
### 自动生成
- 谷歌AutoValue开源框架
## 11. 重写 equals 方法时同时也要重写 hashcode 方法
### 约定
1. 重复调用hashCode返回相同的值
2. equals(Object)相等，hashCode相等
3. 为不相等的对象生成不同的结果可能会提高hash tables的性能。
### 写法
1. int result = 重要属性c.hashCode(areaCode);
2. result = 31 - result + 剩余的重要属性f.hashCode;
    1. 基本类型：Type.hashCode(f)
    2. 引用类型：
        - equals递归比较：递归调用hashCode
        - 否则计算范式：例如字段为空，使用0
    3. 数组：
        - 部分重要元素：递归计算哈希码并合并
        - 没有重要的元素：使用不为0常量
        - 所有元素都重要：使用Arrays.hashCode
3. return result;
## 12. 始终重写 toString 方法
## 13. 谨慎地重写 clone 方法
- 复制功能最好由构造方法或工厂提供
## 14. 考虑实现 Comparable 接口
### 约定
- 自反性：x.compareTo(y) == 0，则sgn(x.compareTo(z)) == sgn(y.compareTo(z))
- 对称性：sgn(x.compareTo(y)) == -sgn(y. compareTo(x))
- 传递性：(x.compareTo(y) > 0 && y.compareTo(z) > 0)，则x.compareTo(z) > 0 
- 推荐：(x.compareTo(y) == 0) == (x.equals(y))
    - 反例：添加new BigDecimal("1.0")和new BigDecimal("1.00")
        - HashSet：调用equals方法 集合中两个元素
        - TreeSet：调用compareTo方法 集合中一个元素
### 写法
在compareTo方法中使用关系运算符「<」和「>」是冗长且容易出错的，不推荐
```
// Multiple-field `Comparable` with primitive fields
public int compareTo(PhoneNumber pn) {
  int result = [Short.compare(areaCode](http://Short.compare(areaCode), pn.areaCode);
  if (result == 0) {
    result = [Short.compare(prefix](http://Short.compare(prefix), pn.prefix);
    if (result == 0)
      result = [Short.compare(lineNum](http://Short.compare(lineNum), pn.lineNum);
 }
  return result;
}

// Comparable with comparator construction methods
private static final Comparator<PhoneNumber> COMPARATOR =
    comparingInt((PhoneNumber pn) -> pn.areaCode)
    .thenComparingInt(pn -> pn.prefix)
    .thenComparingInt(pn -> pn.lineNum);
public int compareTo(PhoneNumber pn) {
  return COMPARATOR.compare(this, pn);
}
```
## 15. 使类和成员的可访问性最小化
- 类具有公共静态final数组属性，或返回这样一个属性的访问器是错误的
```
// Potential security hole!
public static final Thing[] VALUES = { ... };

//解决方案
//方案1：添加一个公共的不可变列表
private static final Thing[] PRIVATE_VALUES = { ... };
public static final List<Thing> VALUES =
Collections.unmodifiableList(Arrays.asList(PRIVATE_VALUES));

//方案2：添加一个返回私有数组拷贝
private static final Thing[] PRIVATE_VALUES = { ... };
public static final Thing[] values() {
  return PRIVATE_VALUES.clone();
}
```
## 16. 在公共类中使用访问方法而不是公共属性
## 17. 最小化可变性
### 不可变类
String、基本类型包装类、BigInteger、BigDecimal
### 规则
1. 不要提供修改对象状态的方法（也称为 mutators）
2. 确保这个类不能被继承
3. 把所有属性设置为 final
4. 把所有的属性设置为 private
5. 确保对任何可变组件的互斥访问
### 优点
- 不可变对象很简单
- 不可变对象本质上是线程安全的
- 不可变对象为其他对象提供了很好的构件
- 不可变对象提供了免费的原子失败机制
### 缺点
- 对于每个不同的值都需要一个单独的对象
### 实现
```
// Immutable class with static factories instead of constructors
public class Complex {
  private final double re;
  private final double im;
  private Complex(double re, double im) {
    this.re = re;
    this.im = im;
 }
  public static Complex valueOf(double re, double im) {
    return new Complex(re, im);
 }
 ... // Remainder unchanged
}
```
## 18. 组合优于继承
- InstrumentedHashSet
- 使用包装类（Decorator）ForwardingSet 改进
- 只有「is-a」关系，才能使用继承 反例：Stack extends Vector；Properties extends Hashtable
## 19. 要么设计继承并提供文档说明，要么禁用继承
## 20. 接口优于抽象类
- 通过抽象类实现接口默认方法 例如：AbstractList implements List
## 21. 为后代设计接口
## 22. 接口仅用来定义类型
- 常量接口模式是对接口的糟糕使用
## 23. 类层次结构优于标签类
## 24. 支持使用静态成员类而不是非静态类
- 如果你声明了一个不需要访问宿主实例的成员类，总是把 static 修饰符放在它的声明中，使它成为一个静态成员
  类，而不是非静态的成员类
## 25. 将源文件限制为单个顶级类
## 26. 不要使用原始类型
## 27. 消除非检查警告
## 28. 列表优于数组
## 29. 优先考虑泛型
## 30. 优先使用泛型方法
## 31. 使用限定通配符来增加 API 的灵活性
## 32. 合理地结合泛型和可变参数
## 33. 优先考虑类型安全的异构容器
## 34. 使用枚举类型替代整型常量
## 35. 使用实例属性替代序数
## 36. 使用 EnumSet 替代位属性
## 37. 使用 EnumMap 替代序数索引
## 38. 使用接口模拟可扩展的枚举
## 39. 注解优于命名模式
## 40. 始终使用 Override 注解
## 41. 使用标记接口定义类型
## 42. lambda 表达式优于匿名类
## 43. 方法引用优于 lambda 表达式
## 44. 优先使用标准的函数式接口
## 45. 明智审慎地使用 Stream
## 46. 优先考虑流中无副作用的函数
## 47. 优先使用 Collection 而不是 Stream 来作为方法的返回类型
## 48. 谨慎使用流并行
## 49. 检查参数有效性
- Objects.requireNonNull
## 50. 必要时进行防御性拷贝
## 51. 仔细设计方法签名
## 52. 明智审慎地使用重载
## 53. 明智审慎地使用可变参数
## 54. 返回空的数组或集合，不要返回 null
- Collections.emptyList
## 55. 明智审慎地返回 Optional
## 56. 为所有已公开的 API 元素编写文档注释
## 57. 最小化局部变量的作用域
## 58. for-each 循环优于传统 for 循环
## 59. 了解并使用库
- 不使用Random,用ThreadLocalRandom
- 每个程序员都应该熟悉 java.lang、java.util 和 java.io 的基础知识及其子包
## 60. 若需要精确答案就应避免使用 float 和 double 类型
## 61. 基本数据类型优于包装类
- 将 == 操作符应用于包装类型几乎都是错误的
## 62. 当使用其他类型更合适时应避免使用字符串
## 63. 当心字符串连接引起的性能问题
## 64. 通过接口引用对象
## 65. 接口优于反射
## 66. 明智审慎地本地方法
## 67. 明智审慎地进行优化
## 68. 遵守被广泛认可的命名约定
- 转换对象类型（返回不同类型的独立对象）的实例方法通常称为 toType，例如
  toString 或 toArray。返回与接收对象类型不同的视图的方法通常称为 asType，例如 asList。返回与调
  用它们的对象具有相同值的基本类型的方法通常称为类型值，例如 intValue。静态工厂的常见名称包括 from、of、
  valueOf、instance、getInstance、newInstance、getType 和 newType
## 69. 只针对异常的情况下才使用异常
## 70. 对可恢复的情况使用受检异常，对编程错误使用运行时异常
## 71. 避免不必要的使用受检异常
## 72. 优先使用标准的异常
## 73. 抛出与抽象对应的异常
## 74. 每个方法抛出的异常都需要创建文档
## 75. 在细节消息中包含失败一捕获信息
## 76. 保持失败原子性
## 77. 不要忽略异常
## 78. 同步访问共享的可变数据
## 79. 避免过度同步
## 80. executor 、task 和 stream 优先于线程
## 81. 并发工具优于 wait 和 notify
## 82. 文档应包含线程安全属性
## 83. 明智审慎的使用延迟初始化
## 84. 不要依赖线程调度器
## 85. 优先选择 Java 序列化的替代方案
## 86. 非常谨慎地实现 Serializable
## 87. 考虑使用自定义的序列化形式
## 88. 保护性的编写 readObject 方法
## 89. 对于实例控制，枚举类型优于 readResolve
## 90. 考虑用序列化代理代替序列化实例