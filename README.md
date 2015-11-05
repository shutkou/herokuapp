# herokuapp
challenge solution


* prereq: JAVA 1.8, MAVEN 3.3


#To run: 



mvn test , or 

mvn test -Dbrowser=chrome , or

mvn test -Dbrowser=firefox , or

run testng.xml (right click on the testng.xml -> runAs testNG)

#Solution workflow:

1. Loads Inventory price list and State taxes from supplied xls file

2. Verifies the prices on the order page match with the file

3. Verifies the quantity on the order page match with the file

4. Submits order for each State given in the file.

5. Verifies the prices on the checkout page match with the file

6. Verifies the quantity on the checkout page match with the file

7. Verifies subtotal

8. Verifies taxes

9. Verifies total
