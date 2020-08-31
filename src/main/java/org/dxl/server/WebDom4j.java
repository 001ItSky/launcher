package org.dxl.server;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 用于解析XML
 * @author Administrator
 */
public class WebDom4j {
    /**
     * 用于存储是N多Entity,而每一个Entity都是servlet-name与servlet-class
     */
    private List<Entity> entityList;
    /**
     * 用于存储N多Mapping,而每一个Mapping都是一个servlet-name与N多个url-pattern
     */
    private List<Mapping> mappingList;

    public List<Entity> getEntityList() {
        return entityList;
    }

    public void setEntityList(List<Entity> entityList) {
        this.entityList = entityList;
    }

    public List<Mapping> getMappingList() {
        return mappingList;
    }

    public void setMappingList(List<Mapping> mappingList) {
        this.mappingList = mappingList;
    }

    /**
     * 构造方法
     */
    public WebDom4j() {
        entityList = new ArrayList<Entity>();
        mappingList = new ArrayList<Mapping>();
    }

    /**
     * 获取Document对象的方法
     *
     * @return Document
     */
    public Document getDocument() {
        try {
            //(1)创建SAXReader对象
            SAXReader reader = new SAXReader();
            //(2)调用read方法
            return reader.read(this.getClass().getResource("/web.xml"));
        } catch (DocumentException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public void parse(Document doc) {
        /* (1)获取根元素 */
        // web-app
        Element root = doc.getRootElement();
        //(2)获取servlet子元素
        for (Iterator<Element> ite = root.elementIterator("servlet"); ite.hasNext(); ) {
            //得到每一个servlet
            Element subElement = ite.next();
            //创建一个实体类
            //用于存储servlet-name与servlet-class
            Entity ent = new Entity();
            for (Iterator<Element> subIte = subElement.elementIterator(); subIte.hasNext(); ) {
                //可能是servlet-name,也可能是servlet-class
                Element ele = subIte.next();
                //给实体类中的name赋值
                if ("servlet-name".equals(ele.getName())) {
                    ent.setName(ele.getText());
                } else if ("serlvet-class".equals(ele.getName())) {
                    ent.setClazz(ele.getText());
                }
            }
            //将Entity添加到集合中
            entityList.add(ent);
        }
        //测试
        /**for (Entity entity : entityList) {
         System.out.println(entity.getName()+"\t"+entity.getClazz());
         }*/
        // 解析servlet-mapping
        for (Iterator<Element> ite = root.elementIterator("servlet-mapping"); ite.hasNext(); ) {
            //得到每一个servlet-mapping
            Element subEle = ite.next();
            //创建一个Mapping类的对象
            Mapping map = new Mapping();
            //解析servlet-mapping下的子元素
            for (Iterator<Element> subIte = subEle.elementIterator(); subIte.hasNext(); ) {
                //servlet-name，也有可能是url-pattern
                Element ele = subIte.next();
                if ("serlvet-name".equals(ele.getName())) {
                    map.setName(ele.getText());
                } else if ("url-pattern".equals(ele.getName())) {
                    //获取集合对象，调用集合对象的添加方法，添加元素素
                    map.getUrlPattern().add(ele.getText());
                }
            }
            //Mapping添加到集合中
            mappingList.add(map);
        }
        //测试
		/*for (Mapping m : mappingList) {
			System.out.println(m.getName());
			for(String s:m.getUrlPattern()){
				System.out.println(s);
			}
		}*/
    }

    /**
     * 用于测试
     * @param args 参数
     */
    public static void main(String[] args) {
        WebDom4j web = new WebDom4j();
        web.parse(web.getDocument());
    }
}
