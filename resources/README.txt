project folder:
pavlosP1967_project04/


Brief description of submitted files:

pavlosP1967_project05/src/lazyTrees/SuperMarket.java
	- Simulates the market scenario of buying and adding items to a store's inventory.
      Implements BST with lazy deletion to keep track of total inventory ("deleted" + non deleted) 
      and current inventory (non deleted only).It contains main()
    

pavlosP1967_project05/src/lazyTrees/LazySearchTree.java
    - The Class LazySearchTree stores Items in a BST and performs lazy deletions of Items.
      It prints both lazy deleted and non lazy deleted. The find max and min methods considering
      only the lazy deleted Items.


pavlosP1967_project05/src/lazyTrees/FoothillTunesStore.java
   - The Class FoothillTunesStore has user menu for add, remove, print hard and soft,
     show minimum and maximum values of Songs. It contains main()

    
pavlosP1967_project05/src/lazyTrees/SongEntry.java
  - The Class SongEntry reads and inserts songs read from a jason file
     to the LazySearchTree.One Object stores also sample songs read from a txt file.
 
    
pavlosP1967_project05/src/lazyTrees/Item.java
  - One object of Item class represents one item in the inventory, with two class members.
  
  
 pavlosP1967_project05/src/lazyTrees/Traverser.java
   - Traverser Interface with one print method
 
 
pavlosP1967_project05/resources/music_genre_subset.jason
   - JSON file contains all the songs
 
   
pavlosP1967_project05/resources/inventory_log.txt
   - Test file for the LazySearchTree by adding and removing items from the inventory
   
 
pavlosP1967_project05/resources/inventory_short.txt
   - Short inventory test file to test for removal of root node from LazySearchTree
   
   
pavlosP1967_project05/resources/test.txt
   - Short test file of testing the boundary condition when removing all the items
   
RUN.txt
    - console output of SuperMarket.java
    - console output of FoothillTunesStore.java

README.txt
    - description of submitted files