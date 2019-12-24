---
title: "数据库"
date: 2019-12-02
description: ""
tags: [sql]
---

### 建表
```
CREATE INDEX idx_table_name_column_name ON table_name(column_name);
CREATE UNIQUE INDEX idx_table_name_column_name ON table_name(column_name);
CREATE sequence seq_table_name;
COMMENT ON TABLE table_name IS 'table_name';
COMMENT ON COLUMN table_name.column_name IS 'column_name';
```
### 修改表
```
DROP sequence seq_name;
ALTER TABLE table_name ADD (column_name datatype,column_name datatype);
ALTER TABLE table_name MODIFY (column_name datatype,column_name datatype);
ALTER TABLE table_name DROP (column_name,column_name);
```
### 复制表
`CREATE TABLE NEW_TAB AS SELECT * FROM OLD_TAB WHERE 1=1; -- WHERE 1=0 不复制数据`
### 查表
`select seq_name.NextVal as id from dual;`
### 授权
```
grant select on table to user;
grant execute on function to user;
```
### 存储过程
```
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
### 触发器
```
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
### 定时任务
```
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
    dbms_job.run(123);--和select * from user_jobs; 中的job值对应，看what对应的过程
end;
```
### dblink
```
CREATE public database link test_link CONNECT TO user IDENTIFIED BY "pwd"
using '(DESCRIPTION = (ADDRESS_LIST = (ADDRESS = (PROTOCOL = TCP)(HOST = 127.0.0.1)(PORT = 1521)) ) (CONNECT_DATA = (SERVICE_NAME = LEE) ) )'; 

select * from table@test_link; 
drop public database link TEST_LINK;
```
### char varchar nchar nvarchar
```
char 定长
varchar 变长
char、varchar 英文、数字
nchar、nvarchar 中文
```
### 二进制解决多状态问题
1. 2次幂做id，每一位保存一种状态
    ```
    00000001 1
    00000010 2
    00000100 4
    00001000 8
    ```
2. 多选时，做|运算，记为`result_id`
3. 查询时，用`bitand(id,result_id)>0`

### 多行合并
```
SELECT listagg (T .ENAME, ',') WITHIN GROUP (ORDER BY T .ENAME) names
FROM SCOTT.EMP T
```