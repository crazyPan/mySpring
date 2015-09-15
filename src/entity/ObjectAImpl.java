package entity;

public class ObjectAImpl implements ObjectA {
	private ObjectB b;

	public ObjectAImpl() {
		b = new ObjectBImpl();
	}
	
	public void setB(ObjectB b) {
		this.b = b;
	}

	public ObjectB getB() {
		return b;
	}
	
}
