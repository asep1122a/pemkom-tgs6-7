package app;

import com.mongodb.client.*;
import org.bson.Document;
import java.util.ArrayList;
import java.util.List;

public class cs {
    private final MongoCollection<Document> collection;

    public cs() {
        this.collection = MongoConnection.getDatabase().getCollection("tugas");
    }

    public void insertCustomer(customer customer) {
        Document doc = new Document("name", customer.getName())
                .append("email", customer.getEmail());
        collection.insertOne(doc);
    }

    public List<customer> getAllCustomers() {
        List<customer> list = new ArrayList<>();
        FindIterable<Document> docs = collection.find();
        for (Document doc : docs) {
            customer customer = new customer();
            customer.setId(doc.getObjectId("_id").toHexString());
            customer.setName(doc.getString("name"));
            customer.setEmail(doc.getString("email"));
            list.add(customer);
        }
        return list;
    }

    public void deleteCustomer(String id) {
        collection.deleteOne(new Document("_id", new org.bson.types.ObjectId(id)));
    }
}
