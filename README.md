# Airplane-2D-Animation

This is a 2D animation of plane flying in the sky with clouds, where sky changes it's color and stars appear. Run this animation from the main method in PlaneSimulation.java class. 

![image](https://user-images.githubusercontent.com/84543584/181781255-7e9e4149-d647-400d-9646-085cf022bd1f.png)

![image](https://user-images.githubusercontent.com/84543584/181781266-dab2c118-2e19-4d3a-832b-3daa7573d0fa.png)


## Class architecture 
The plane class models a plane, and has methods for moving the plane( changing it's cordinates) such as *steadyflight()*, *takeOff()* and *flyAway()*. It also plays the sounds depending on which of these  methods is running. Cloud models a cloud, and Clouds class is responsible for movement of all clouds in the screen, size, reseting their position and so on. A sky class is responsible for changing the day into a night, and appearence of stars. Animation is done in the View class which extends JPanel and has the method *paint()* in.

![image](https://user-images.githubusercontent.com/84543584/181781094-22129f5d-b070-4ee0-bf7d-6fe9cab2d517.png)

## Plane movement 

The plane launches by changing it's position on every frame based on two functions. From the knowledge of basic mathematic functions, the combination of exponentential function with logarithmitc gave a nice launching animation. By playing around with these functions in desmos, functions (1) and (2) were chosen. The plane first starts with function %f_1(x)$ until $x$ is equal to $0$, then it continues with the second function. $X$ is incremented every frame for some pixels.

$$f_1(x) = 2^x$$
$$g_1(x) = 1+log(x+1) $$

![image](https://user-images.githubusercontent.com/84543584/181768858-b5367982-e63e-4e80-afa2-ce2455686fe0.png)

However, these functions should be scaled up because a pixel is a small unit in a java panel. Let s be a scale factor, then $f(x)$ and $g(x)$ are scaled up functions of $f_1(x)$ and $g_1(x)$:

$$ f(x)=s*f_1(x/s)=s*2^{x/2} $$

$$ g(x) =s*g_(x/s)=s*(1+log(x/s +1)) $$

For example, if we wish the functions to be 10 times "bigger", we let $s = 10$, and we get

![image](https://user-images.githubusercontent.com/84543584/181769563-0b7d23c7-62b2-46b6-860d-5d18df8878a8.png)

And in Java we center the coordinate system to the center of the frame and we get:

![media1_AdobeExpress](https://user-images.githubusercontent.com/84543584/181772118-c8ccf23d-64ad-411d-b0ff-1f18e9ea8afd.gif)

The red square with now is replaced with a plane icon. When the plane reaches the end of the JFrame, it then goes back by changing only it's $x$ coordinate for some unit pixels every frame. Then when it reaches almost the beggining of the JFrame, it moves up and down by only changing it's $y$ coordinate.

![media2_AdobeExpress](https://user-images.githubusercontent.com/84543584/181773254-f3f901cf-ad66-4fce-88b3-b195b59ed6dd.gif)

## Clouds 

Images of different clouds were inserted on the project. Each cloud is randomly scaled and it has initial random position at the end of the window. So the x coordinate will be equal to the width/2 of the window, and  only the coordinate y is a random number within the height of the window. Then the cloud moves until it reaches the end of the window (by changing x only for some pixel units every frame), and then it gets reseted again at the end of the frame.

![media3_AdobeExpress](https://user-images.githubusercontent.com/84543584/181775036-ecdb22f6-3192-4246-903b-c531a8a32b5e.gif)

## Sky 

The way that sky changes it's color was accomplished by a gradient paint which takes initially two colors $c_1,c_2$ - both blue. Then when it is  day, $c_2$ goes darker every frame, then $c_1$ goes darker every frame and then it becomes night. Next we lighten $c_1, c_2$ colors until they become both blue.

![media4_AdobeExpress](https://user-images.githubusercontent.com/84543584/181776914-6f806d4a-53bc-42a3-a5c0-b876ec90ca07.gif)

When it is starting to become night, clouds are removed and stars start appearing randomly in the sky and are entered in a list. When it starts to become a day then each star is popped from the list until all stars are removed.

![media6_AdobeExpress](https://user-images.githubusercontent.com/84543584/181778918-2e88e3b2-02b1-4c4d-856a-ff2802f0da24.gif)


