## 注解支持

### 空和非空检查

| - | - |
| @NotBlank  | 只能用于字符串不为 null ，并且字符串 #trim() 以后 length 要大于 0  
|@NotEmpty| 集合对象的元素不为 0 ，即集合不为空，也可以用于字符串不为 null 
|@NotNull | 不能为 null 
|@Null | 必须为 null 

### 数值检查

| - | - |
|@DecimalMax(value) |被注释的元素必须是一个数字，其值必须小于等于指定的最大值。
|@DecimalMin(value)|被注释的元素必须是一个数字，其值必须大于等于指定的最小值。
|@Digits(integer, fraction) |被注释的元素必须是一个数字，其值必须在可接受的范围内。
|@Positive |判断正数。
|@PositiveOrZero |判断正数或 0 。
|@Max(value) |该字段的值只能小于或等于该值。
|@Min(value) |该字段的值只能大于或等于该值。
|@Negative |判断负数。
|@NegativeOrZero |判断负数或 0 。


### Boolean 值检查

| - | - |
@AssertFalse |被注释的元素必须为 true 。
@AssertTrue | 被注释的元素必须为 false 。


### 长度检查
| - | - |
@Size(max, min) |检查该字段的 size 是否在 min 和 max 之间，可以是字符串、数组、集合、Map 等。
日期检查
@Future |被注释的元素必须是一个将来的日期。
@FutureOrPresent |判断日期是否是将来或现在日期。
@Past |检查该字段的日期是在过去。
@PastOrPresent |判断日期是否是过去或现在日期。


### 其它检查
| - | - |
@Email |被注释的元素必须是电子邮箱地址。
@Pattern(value) |被注释的元素必须符合指定的正则表达式。