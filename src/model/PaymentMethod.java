package model;

public enum PaymentMethod {
	creditcard,
	cash,
	invoice;
	
  @Override
  public String toString() {
    switch(this) {
      case cash: return "cash";
      case creditcard: return "creditcard";
	  case invoice: return "invoice";
      default: throw new IllegalArgumentException();
    }
  }
}
