# lazyinit
This repo is to demo the case of lazy-init exception thrown while accessing a lazily loaded child ORM object which is not initialised properly.

So the controller here exposes 2 endpoints - /parents/child/name/throwsEx & /parents/child/name/correctApproach. 

Former will throw the exception because it tries to access the properties of the child object is lazily loaded through the parent. The latter will ensure the child object is loaded correctly before accessing its properties. 

## Steps to experience the exception 
1. Run the spring-boot app
2. Submit this curl POST request `curl /parents/child/name/throwsEx`
3. Observe the LazyInitException thrown up in the server log

## Steps to experience the correct way of accessing the child property 
1. Run the spring-boot app
2. Submit this curl POST request `curl /parents/child/name/correctApproach`
3. Notice it returns "thing" which is the name of the child object.


