public class RectangleClass
{
	private int x;
	private int y;
	private int width;
	private int height;


	public int getX() {
		return x;
	}


	public int getY() {
		return y;
	}


	public int getWidth() {
		return width;
	}


	public int getHeight() {
		return height;
	}


	public RectangleClass(int x, int y, int width, int height){
		this.x=x;
		this.y=y;
		this.width=width;
		this.height=height;
	}

	// Rectangle r= new Rectangle(1,2,3,4);
	//r.checkWorldBox();
	public boolean checkWorldBox() {
		//This boolean to check the width , height , x , y
		boolean check=true;
		if(x<0||y<0||width<=0||height<=0||(x+width)>1024||(y+height)>1024) {
			check=false;
			return check;
		}
		return check;

	}

	public boolean checkRegionSearch() {
		boolean checkSearch=true;
		if(width<=0 || height<=0) {
			checkSearch=false;
			return checkSearch;
		}
		return checkSearch;
	}

	// to print value of getValue() in KVPair class
	@Override
	public String toString(){
		String output= x + ", " + y + ", " + width + ", " + height ;
		return output;
	}

	//Rectangle r1=new Rectangle(1,2,3,4);
	//Rectangle r2=new Rectangle(4,5,6,7);
	//r1.equals(r2);
	public boolean equals(Object o){
		boolean equ=true;
		RectangleClass r2= (RectangleClass) o;
		if(this.x==r2.x && this.y==r2.y && this.width==r2.width && this.height==r2.height) {
			return equ;
		} else {
			equ = false;
			return equ;
		}
	}

	public boolean Intersect(RectangleClass r2){
		boolean intersect=true;
		if(this.x< r2.x+r2.width && this.x+this.width>r2.x && this.y<r2.y+ r2.height && this.y+this.height>r2.y)
		{
			return intersect;
		}
		else {
			intersect=false;
			return intersect;
		}
	}

}
