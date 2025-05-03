package app;

import com.mongodb.client.*;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private final MongoCollection<Document> collection;

    public CustomerService() {
        this.collection = MongoConnection.getDatabase().getCollection("tugas");
    }

    public void addCustomer(Customer customer) {
        Document doc = new Document("name", customer.getName())
                .append("phoneNumber", customer.getPhoneNumber()); // Mengubah email menjadi phoneNumber
        collection.insertOne(doc);
    }

    public List<Customer> fetchAllCustomers() {
        List<Customer> list = new ArrayList<>();
        FindIterable<Document> docs = collection.find();
        for (Document doc : docs) {
            Customer customer = new Customer();
            customer.setId(doc.getObjectId("_id").toHexString());
            customer.setName(doc.getString("name"));
            customer.setPhoneNumber(doc.getString("phoneNumber")); // Mengubah email menjadi phoneNumber
            list.add(customer);
        }
        return list;
    }

    public void removeCustomer(String id) {
        collection.deleteOne(new Document("_id", new org.bson.types.ObjectId(id)));
    }
}
