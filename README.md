# lazyinit
 
1. Run the spring-boot app
2. Submit a POST to `http://localhost:9095/parents/lazyinitexception`
3. Observe the LazyInitException thrown up in the server log
4. Reason
   1. This creates the parent object with a child object
   2. Retrieves the parent object with the 'lazily' loaded child
   3. Invokes a property on the lazily loaded child object. 
5. Fix
   1. Make these call before accessing `child.getName()`
      1. `Child child = childService.retrieve(fetchedParent.getChild().getId());`

