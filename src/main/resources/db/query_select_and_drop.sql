SELECT TOP (1000) [id]
      ,[username]
      ,[password]
      ,[role]
  FROM [mydb].[dbo].[employee]

  DELETE FROM [mydb].[dbo].[employee]
WHERE [id] <= 5;