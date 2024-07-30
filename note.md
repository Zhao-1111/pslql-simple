# 每日进度
### 2024 724
1. 写到了声明变量的执行，我要改变put的key，value 直接为string 和 值
2. symtab的作用，用以检查变量在语法树构建时，是否只存在同名，所以构建语法树时需要检查(7.26完成)
ps:不行，这样会在生成语法树时处理异常。语义检查应该在sc(semanticCheck)去统一完成。

### 2024 726
1. 将上述在生成语法树的symtab，移动到sc阶段,增加类型的sc阶段检查(类型是否可以被赋值)

### 2024 729
1. 语句返回null,表达式返回新的表达
2. 关于赋值，name 对应一个constantValue
3. 完成execute:variablestmt constantstmt assignment 

### 2024 730 
1. 增加sc execute:case, for, if, loop（未完成）
    1. ps1：目前无表达式，完成上面那些，去写表达式
    2. ps2：我必须要先完成表达式，才能去完成stmt，否则后续完成expr后需要大改stmt
2. 完成部分表达式语法树生成 