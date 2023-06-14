# poi 读取doc文件
```xml
<!--   poi读取doc     -->
<!-- https://mvnrepository.com/artifact/org.apache.poi/poi -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>5.2.3</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.2.3</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.apache.poi/poi-ooxml-schemas -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml-schemas</artifactId>
    <version>4.1.2</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.apache.poi/poi-scratchpad -->
<dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-scratchpad</artifactId>
    <version>5.2.3</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core -->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-core</artifactId>
    <version>2.20.0</version>
</dependency>
<!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-api -->
<dependency>
    <groupId>org.apache.logging.log4j</groupId>
    <artifactId>log4j-api</artifactId>
    <version>2.20.0</version>
</dependency>

```

```java

public static void main(String[] args) {
        String filePath = "/Users/wangjiahao/Downloads/加密下单接口.docx";
        String buffer = "";
        try {
            if (filePath.endsWith(".doc")) {
                InputStream is = new FileInputStream(filePath);
                WordExtractor ex = new WordExtractor(is);
                buffer = ex.getText();
                ex.close();
            } else if (filePath.endsWith("docx")) {
                OPCPackage opcPackage = POIXMLDocument.openPackage(filePath);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                buffer = extractor.getText();
                extractor.close();
            } else {
                System.out.println("此文件不是word文件！");
            }
            System.err.println(buffer);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
```

# pdfbox 拼接图片

添加相关依赖

```xml
<!--   拼接pdf     -->
<dependency>
	<groupId>org.apache.pdfbox</groupId>
	<artifactId>pdfbox</artifactId>
	<version>2.0.27</version>
</dependency>
```

创建拼接工具类`PdfBoxUtils`

```java
public class PdfBoxUtils {

    /**
     * 拼接图片到pdf中，拼接方式是PREPEND，默认是覆盖到pdf上层。如需要更改，请修改 PDPageContentStream.AppendMode.PREPEND
     *
     * @param originalPdf pdf源文件
     * @param jointImage  需要拼接的图片
     * @param savePath    保持路径
     * @param pageNum     拼接到页码 页码从0开始
     * @param x           偏移定位 x
     * @param y           偏移定位 y
     * @param width       图片宽
     * @param height      图片高
     * @throws IOException 读取文件异常
     */
    public static void imgInPdf(File originalPdf, File jointImage, String savePath, int pageNum, int x, int y, int width, int height) throws IOException {
        //Loading an existing document
        PDDocument doc = PDDocument.load(originalPdf);
        //Retrieving the page
        PDPage page = doc.getPage(pageNum);
        //Creating PDImageXObject object
        PDImageXObject pdImage = PDImageXObject.createFromFileByExtension(jointImage, doc);
        //creating the PDPageContentStream object
        PDPageContentStream contents = new PDPageContentStream(doc, page, PDPageContentStream.AppendMode.PREPEND, true, false);
        //Drawing the image in the PDF document
        contents.drawImage(pdImage, x, y, width, height);
        System.out.println("Image inserted");
        //Closing the PDPageContentStream object
        contents.close();
        //Saving the document
        doc.save(savePath);
        //Closing the document
        doc.close();
    }
}
```

## pdf 写入文字





# mapstruct

在`pom.xml`中添加以下配置

```xml
<dependencys>
	<!--mapStruct依赖-->
	<dependency>
		<groupId>org.mapstruct</groupId>
		<artifactId>mapstruct</artifactId>
		<version>${mapstruct.version}</version>
	</dependency>
	<dependency>
		<groupId>org.mapstruct</groupId>
		<artifactId>mapstruct-processor</artifactId>
		<version>${mapstruct.version}</version>
		<scope>provided</scope>
	</dependency>
</dependencys>
```

创建一个用户的基本信息`UserInfo.class`
















