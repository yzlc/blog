---
title: "sql"
date: 2019-05-04T10:32:33+08:00
tags: [sql]
categories: [笔记]
---

## 建表
```sql
CREATE INDEX idx_table_name_column_name ON table_name(column_name);
CREATE UNIQUE INDEX idx_table_name_column_name ON table_name(column_name);
CREATE sequence seq_table_name;
COMMENT ON TABLE table_name IS 'table_name';
COMMENT ON COLUMN table_name.column_name IS 'column_name';
```

## 修改表
```sql
DROP sequence seq_name;
ALTER TABLE table_name ADD (column_name datatype,column_name datatype);
ALTER TABLE table_name MODIFY (column_name datatype,column_name datatype);
ALTER TABLE table_name DROP (column_name,column_name);
```

## 复制表
```sql
CREATE TABLE NEW_TAB AS SELECT * FROM OLD_TAB WHERE 1=1; -- WHERE 1=0 不复制数据
```

## 查表
```sql
select seq_name.NextVal as id from dual;
```

## 授权
```sql
grant select on table to user;
grant execute on function to user;
```

## 存储过程
```sql
create procedure sync_table_from_user is
    v_err varchar(2000);
begin
    UPDATE SET ID=t.ID

    commit;
EXCEPTION
    WHEN NO_DATA_FOUND THEN
        rollback;
    WHEN OTHERS THEN
        rollback;
end;
```

## 触发器
```sql
create trigger TG_TABLE_LOG
    before update
    on TABLE
    for each row
DECLARE
BEGIN
    insert into TABLE(id)
    values (:old.id);
END;
```

## 定时任务
```sql
declare
    job number;
BEGIN
    DBMS_JOB.SUBMIT(
            JOB => job,  /*自动生成JOB_ID*/
            WHAT => 'procedure;',  /*需要执行的存储过程名称或SQL语句*/
            NEXT_DATE => sysdate,  /*初次执行时间*/
            INTERVAL => 'trunc(sysdate,''mi'')+5/(24*60)' /*每隔5分钟执行一次*/
        );
    commit;
end;

select * from all_jobs;

begin
    dbms_job.run(123);--启动
    dbms_job.broken(123, true);--停止
end;
```

## dblink
```sql
CREATE public database link test_link CONNECT TO user IDENTIFIED BY "pwd"
using '(DESCRIPTION = (ADDRESS_LIST = (ADDRESS = (PROTOCOL = TCP)(HOST = 127.0.0.1)(PORT = 1521)) ) (CONNECT_DATA = (SERVICE_NAME = LEE) ) )'; 

select * from table@test_link; 
drop public database link TEST_LINK;
```

## char varchar nchar nvarchar
```sql
char 定长
varchar 变长
char、varchar 英文、数字
nchar、nvarchar 中文
```

## 二进制解决多状态问题
1. 2次幂做id，每一位保存一种状态
    ```
    00000001 1
    00000010 2
    00000100 4
    00001000 8
    ```
2. 多选时，做|运算，记为`result_id`
3. 查询时，用`bitand(id,result_id)>0`

## 多行合并
```sql
SELECT listagg (T .ENAME, ',') WITHIN GROUP (ORDER BY T .ENAME) names
FROM SCOTT.EMP T
```

## 截取字符串
```sql
SELECT SUBSTR ('123.456', INSTR ('123.456', '.', 1, 1)+1) FROM DUAL;
```

## 窗口函数
### 语法
`<函数> over (partition by <分组列> order by <排序列>)`

### 分类
- 窗口函数（行数不变）
  - rank（并列占位）
  - dense_rank（并列不占位）
  - row_number（不并列）
- 聚合函数（行数变，作用范围自身及以上数据）：sum，avg，count，max，min

## 优化
1. 评估查询条件、表关联、子查询对性能的影响
2. 针对性能差的部分做优化（加索引、窗口函数）
3. 清除缓存再测试
    ```sql
    ALTER SYSTEM FLUSH SHARED_POOL;
    ALTER SYSTEM FLUSH BUFFER_CACHE;
    ALTER SYSTEM FLUSH GLOBAL CONTEXT;
    ```