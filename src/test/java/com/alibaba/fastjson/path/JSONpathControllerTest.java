package com.alibaba.fastjson.path;

import java.util.Collection;
import java.util.List;

import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;
import com.alibaba.fastjson.path.Person.Book;

/**
 * <pre>
 * 例子：https://github.com/itguang/gitbook-smile/blob/master/springboot-fastjson/fastjson%E4%B9%8BJSONPath%E4%BD%BF%E7%94%A8.md
 * @author onlyone
 * </pre>
 */
public class JSONpathControllerTest {

    @Test
    public void readPath() {
        String jsonStr = "{\n" +
                "    \"store\": {\n" +
                "        \"bicycle\": {\n" +
                "            \"color\": \"red\",\n" +
                "            \"price\": 19.95\n" +
                "        },\n" +
                "        \"book\": [\n" +
                "            {\n" +
                "                \"author\": \"刘慈欣\",\n" +
                "                \"price\": 8.95,\n" +
                "                \"category\": \"科幻\",\n" +
                "                \"title\": \"三体\"\n" +
                "            },\n" +
                "            {\n" +
                "                \"author\": \"itguang\",\n" +
                "                \"price\": 12.99,\n" +
                "                \"category\": \"编程语言\",\n" +
                "                \"title\": \"go语言实战\"\n" +
                "            }\n" +
                "        ]\n" +
                "    }\n" +
                "}";
        
        JSONObject jsonObject = JSON.parseObject(jsonStr);

        System.out.println(jsonObject.toString());
        // 得到所有的书
        List<Book> books = (List<Book>) JSONPath.eval(jsonObject, "$.store.book");
        System.out.println("books={}" + books);

        // 得到所有的书名
        List<String> titles = (List<String>) JSONPath.eval(jsonObject, "$.store.book.title");
        System.out.println("titles={}" + titles);

        // 第一本书title
        String title = (String) JSONPath.read(jsonStr, "$.store.book[0].title");
        System.out.println("title={}" + title);

        // price大于10元的book
        List<Book> list = (List<Book>) JSONPath.read(jsonStr, "$.store.book[price > 10]");
        System.out.println("price大于10元的book={}" + list);

        // price大于10元的title
        List<String> list2 = (List<String>) JSONPath.read(jsonStr, "$.store.book[price > 10].title");
        System.out.println("price大于10元的title={}" + list2);

        // category(类别)为科幻的book
        List<Book> list3 = (List<Book>) JSONPath.read(jsonStr, "$.store.book[category = '科幻']");
        System.out.println("category(类别)为科幻的book={}" + list3);

        // bicycle的所有属性值

        Collection<String> values = (Collection<String>) JSONPath.eval(jsonObject, "$.store.bicycle.*");

        System.out.println("bicycle的所有属性值={}" + values);

        // bicycle的color和price属性值
        List<String> read = (List<String>) JSONPath.read(jsonStr, "$.store.bicycle['color','price']");

        System.out.println("bicycle的color和price属性值={}" + read);

    }

    @Test
    public void setPath() {
        JSONObject object = new JSONObject();
        JSONPath.set(object, "store.bicycle.color", "red");
        JSONPath.set(object, "store.bicycle.price", 8.6);
        JSONPath.set(object, "book.author", "java入门");
        System.out.println(object.toJSONString());
    }

}
