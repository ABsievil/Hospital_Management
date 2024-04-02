SELECT TOP (1000) [id]
      ,[username]
      ,[password]
      ,[role]
  FROM [mydb].[dbo].[employee]

  DELETE FROM [mydb].[dbo].[employee]
WHERE [id] <= 5;

UPDATE [mydb].[dbo].[employee]
SET password = '$2a$10$mntT7V2v0vGiZHIB5O1/XuqfGJSH94ZyNIM7ATH6/q/.zu5rOSUJ2'
WHERE id >= 1 AND id <=3;