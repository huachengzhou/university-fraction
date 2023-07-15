package com.ng.todo.common.utils;

import cn.hutool.core.collection.CollUtil;
import com.aspose.words.Shape;
import com.aspose.words.*;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.ng.todo.pojo.dto.MergeCellModel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.util.List;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kings on 2018-6-6.
 */
public class AsposeUtils {
    public static String reportReplaceString = "\\$\\{(.*?)\\}";
    private static final Logger logger = LoggerFactory.getLogger(AsposeUtils.class);
    //根据书签替换word 内容

    //获取所有书签
    public static BookmarkCollection getBookmarks(Document doc) {
        BookmarkCollection collection = doc.getRange().getBookmarks();
        return collection;
    }


    /**
     * 利用 ascii 码 配合正则 提取中文
     *
     * @param paramValue
     * @return
     */
    public static String getChinese(String paramValue) {
        String regex = "([\u4e00-\u9fa5]+)";
        String str = "";
        Matcher matcher = Pattern.compile(regex).matcher(paramValue);
        while (matcher.find()) {
            str += matcher.group(0);
        }
        return str;
    }


    /**
     * 根据正则表达式 获取匹配的字符串集合
     * example: input \$\{.*?\} ,output:${委托人}
     *
     * @param document
     * @param pattern  可以为null,不过会采用默认的\$\{.*?\}
     * @return <${公共机构名称},公共机构名称>
     */
    public static Map<String, String> getRegexMaps(Document document, String pattern) {
        Map<String, String> map = new LinkedHashMap<>();
        StringBuilder stringBuilder = new StringBuilder();
        //获取所有段落
        Iterator<Section> iterator = document.getSections().iterator();
        while (iterator.hasNext()) {
            Section section = iterator.next();
            ParagraphCollection paragraphs = section.getBody().getParagraphs();
            for (int i = 0; i < paragraphs.toArray().length; i++) {
                stringBuilder.append(paragraphs.get(i).getText());
            }
        }
        stringBuilder.append(document.getText());
        stringBuilder.append(getWordTableText(document));
        Matcher m = Pattern.compile(StringUtils.isNotBlank(pattern) ? pattern : reportReplaceString).matcher(stringBuilder.toString());
        while (m.find()) {
            map.put(m.group(), m.group(1));
        }
        return map;
    }

    /**
     * 根据正则表达式 获取匹配的字符串集合
     *
     * @param content
     * @param pattern
     * @return
     */
    public static Map<String, String> getRegexMaps(String content, String pattern) {
        Map<String, String> map = new LinkedHashMap<>();
        Matcher m = Pattern.compile(StringUtils.isNotBlank(pattern) ? pattern : reportReplaceString).matcher(content);
        while (m.find()) {
            map.put(m.group(), m.group(1));
        }
        return map;
    }

    public static Map<String, String> getRegexExtendList(Document document) {
        return getRegexMaps(document, reportReplaceString);
    }

    /**
     * 使用aspose组件获取表格内容
     *
     * @param document
     * @return
     */
    public static String getWordTableText(Document document) {
        StringBuilder stringBuilder = new StringBuilder(1024);
        NodeCollection nodeCollection = null;
        try {
            nodeCollection = document.getChildNodes(NodeType.TABLE, true);
        } catch (Exception e) {
        }
        if (nodeCollection == null) {
            return stringBuilder.toString();
        }
        if (nodeCollection.getCount() == 0) {
            return stringBuilder.toString();
        }
        for (Node node : nodeCollection.toArray()) {
            Table table = (Table) node;
            RowCollection rows = table.getRows();
            if (rows.getCount() == 0) {
                continue;
            }
            for (Row row : rows.toArray()) {
                CellCollection cells = row.getCells();
                if (cells.getCount() == 0) {
                    continue;
                }
                for (int i = 0; i < cells.getCount(); i++) {
                    Cell cell = cells.get(i);
                    if (StringUtils.isEmpty(cell.getText())) {
                        continue;
                    }
                    stringBuilder.append(cell.getText());
                }
            }
        }
        return stringBuilder.toString();
    }


    /**
     * We want to merge the range of cells found in between these two cells.
     * Cell cellStartRange = table.getRows().get(0).getCells().get(0); //第1行第1列
     * Cell cellEndRange = table.getRows().get(1).getCells().get(0); //第2行第1列
     * Merge all the cells between the two specified cells into one.
     * mergeCells(cellStartRange, cellEndRange, table);
     * aspose word中的表格合并
     *
     * @param startCell
     * @param endCell
     * @param parentTable
     */
    public static void mergeCells(Cell startCell, Cell endCell, Table parentTable) {
        // Find the row and cell indices for the start and end cell.
        Point startCellPos = new Point(startCell.getParentRow().indexOf(startCell), parentTable.indexOf(startCell.getParentRow()));
        Point endCellPos = new Point(endCell.getParentRow().indexOf(endCell), parentTable.indexOf(endCell.getParentRow()));
        // Create the range of cells to be merged based off these indices. Inverse each index if the end cell if before the start cell.
        Rectangle mergeRange = new Rectangle(
                Math.min(startCellPos.x, endCellPos.x),
                Math.min(startCellPos.y, endCellPos.y),
                Math.abs(endCellPos.x - startCellPos.x) + 1,
                Math.abs(endCellPos.y - startCellPos.y) + 1
        );
        for (Row row : parentTable.getRows()) {
            for (Cell cell : row.getCells()) {
                Point currentPos = new Point(row.indexOf(cell), parentTable.indexOf(row));

                // Check if the current cell is inside our merge range then merge it.
                if (mergeRange.contains(currentPos)) {
                    if (currentPos.x == mergeRange.x)
                        cell.getCellFormat().setHorizontalMerge(CellMerge.FIRST);
                    else
                        cell.getCellFormat().setHorizontalMerge(CellMerge.PREVIOUS);

                    if (currentPos.y == mergeRange.y)
                        cell.getCellFormat().setVerticalMerge(CellMerge.FIRST);
                    else
                        cell.getCellFormat().setVerticalMerge(CellMerge.PREVIOUS);
                }
            }
        }
    }

    public static void mergeCellTable(Set<MergeCellModel> mergeCellModelList, Table table) {
        if (CollUtil.isNotEmpty(mergeCellModelList)) {
            for (MergeCellModel mergeCellModel : mergeCellModelList) {
                try {
                    Cell cellStartRange = null;
                    Cell cellEndRange = null;
                    if (mergeCellModel.getCellEndRange() == null && mergeCellModel.getCellStartRange() == null) {
                        cellStartRange = table.getRows().get(mergeCellModel.getStartRowIndex()).getCells().get(mergeCellModel.getStartColumnIndex());
                        cellEndRange = table.getRows().get(mergeCellModel.getEndRowIndex()).getCells().get(mergeCellModel.getEndColumnIndex());
                    } else {
                        cellStartRange = mergeCellModel.getCellStartRange();
                        cellEndRange = mergeCellModel.getCellEndRange();
                    }
                    if (cellStartRange != null && cellEndRange != null) {
                        if (table != null) {
                            AsposeUtils.mergeCells(cellStartRange, cellEndRange, table);
                        }
                    }
                } catch (Exception e) {
                }
            }
        }
    }


    //根据特殊文字替换 word 内容


    /**
     * Map<String, String> map == > 书签名称,需要替换的内容
     * word文档中 书签替换为文档
     *
     * @param filePath       具体文件路径
     * @param map            书签名称,需要替换的内容
     * @param deleteBookMark 是否需要在替换完成时删除书签
     * @throws Exception
     */
    public static void replaceBookmark(String filePath, Map<String, String> map, boolean deleteBookMark) throws Exception {
        List<String> bookmarkList = Lists.newArrayList();
        if (StringUtils.isBlank(filePath)) {
            throw new Exception("error: empty file path");
        }
        if (map == null || map.isEmpty()) {
            throw new Exception("error: empty map");
        }
        Document doc = new Document(filePath);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            Bookmark bookmark = doc.getRange().getBookmarks().get(stringStringEntry.getKey());
            if (bookmark != null) {
                bookmark.setText(stringStringEntry.getValue());
                bookmarkList.add(bookmark.getName());
            }
        }
        doc.save(filePath);
        if (deleteBookMark) {
            Document docDelete = new Document(filePath);
            if (CollUtil.isNotEmpty(bookmarkList)) {
                for (String bookmarkName : bookmarkList) {
                    //删除书签
                    docDelete.getRange().getBookmarks().remove(bookmarkName);
                }
            }
            docDelete.save(filePath);
        }
    }

    //根据特殊文字替换 word 内容

    /**
     * word文档中 替换文本
     *
     * @param filePath 被替换文件路径
     * @param map      key为被替换内容 value为替换内容
     * @throws Exception
     */
    public static Map<String, String> replaceText(String filePath, Map<String, String> map) throws Exception {
        if (StringUtils.isBlank(filePath)) {
            throw new Exception("error: empty file path");
        }
        if (map == null || map.isEmpty()) {
            throw new Exception("error: empty map");
        }
        Map<String, String> stringMap = Maps.newLinkedHashMap();
        Document doc = new Document(filePath);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            if (StringUtils.isNotBlank(stringStringEntry.getKey())) {
                Pattern compile = Pattern.compile(escapeExprSpecialWord(stringStringEntry.getKey()));
                doc.getRange().replace(compile, stringStringEntry.getValue());
            }
        }
        doc.save(filePath);
        return stringMap;
    }

    /**
     * word文档中 替换文本
     *
     * @param filePath
     * @param map
     * @return
     * @throws Exception
     */
    public static Map<String, String> replaceText(String filePath, Multimap<String, String> map) throws Exception {
        if (StringUtils.isBlank(filePath)) {
            throw new Exception("error: empty file path");
        }
        if (map == null || map.isEmpty()) {
            throw new Exception("error: empty map");
        }
        Map<String, String> stringMap = Maps.newLinkedHashMap();
        Document doc = new Document(filePath);
        if (CollUtil.isNotEmpty(map.keySet())) {
            for (String key : map.keySet()) {
                if (StringUtils.isBlank(key)) {
                    continue;
                }
                Collection<String> collection = map.get(key);
                if (CollUtil.isEmpty(collection)) {
                    continue;
                }
                for (String value : collection) {
                    try {
                        doc.getRange().replace(key, value);
                    } catch (Exception e) {
                        stringMap.put(key, value);
                    }
                }
            }
        }
        doc.save(filePath);
        return stringMap;
    }


    /**
     * word文档中 文本替换为word文件
     *
     * @param filePath 被替换文件路径
     * @param map      key为被替换内容 value为附件路径
     */
    public static void replaceTextToFile(String filePath, Map<String, String> map) throws Exception {
        if (StringUtils.isBlank(filePath))
            throw new Exception("error: empty file path");
        if (map == null || map.isEmpty())
            throw new Exception("error: empty map");
        Document doc = new Document(filePath);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            if (StringUtils.isBlank(stringStringEntry.getValue())) {
                continue;
            }
            if (!new File(stringStringEntry.getValue()).isFile()) {
                continue;
            }
            Pattern compile = Pattern.compile(escapeExprSpecialWord(stringStringEntry.getKey()));
            FindReplaceOptions findReplaceOptions = new FindReplaceOptions();
            findReplaceOptions.setReplacingCallback(e -> {
                DocumentBuilder builder = new DocumentBuilder((Document) e.getMatchNode().getDocument());
                builder.moveTo(e.getMatchNode());
                Document document = new Document(stringStringEntry.getValue());
                builder.insertDocument(document, ImportFormatMode.USE_DESTINATION_STYLES);
                return ReplaceAction.REPLACE;
            });
            doc.getRange().replace(compile, "", findReplaceOptions);

        }
        doc.save(filePath);
    }


    /**
     * word文档中文本替换为图片
     *
     * @param filePath
     * @param searchString
     * @param imagePath
     * @throws Exception
     */
    public static void replaceTextToImageFile(String filePath, String searchString, final String imagePath) throws Exception {
        Document fileDoc = new Document(filePath);
        Pattern compile = Pattern.compile(AsposeUtils.escapeExprSpecialWord(searchString));
        IReplacingCallback callback = new IReplacingCallback() {
            @Override
            public int replacing(ReplacingArgs e) throws Exception {
                DocumentBuilder iReplacingCallback = new DocumentBuilder((Document) e.getMatchNode().getDocument());
                iReplacingCallback.moveTo(e.getMatchNode());
                iReplacingCallback.insertImage(imagePath);
                return ReplaceAction.REPLACE;
            }
        };
        FindReplaceOptions findReplaceOptions = new FindReplaceOptions();
        findReplaceOptions.setReplacingCallback(callback);
        fileDoc.getRange().replace(compile, "", findReplaceOptions);
        saveWord(filePath, fileDoc);
    }

    /**
     * word文档中文本替换为HTML
     *
     * @param filePath
     * @param searchString
     * @param html
     * @param useBuilderFormatting
     * @throws Exception
     */
    public static void replaceTextToHtml(String filePath, String searchString, final String html, boolean useBuilderFormatting) throws Exception {
        Document fileDoc = new Document(filePath);
        Pattern compile = Pattern.compile(AsposeUtils.escapeExprSpecialWord(searchString));
        IReplacingCallback callback = new IReplacingCallback() {
            @Override
            public int replacing(ReplacingArgs e) throws Exception {
                DocumentBuilder iReplacingCallback = new DocumentBuilder((Document) e.getMatchNode().getDocument());
                iReplacingCallback.moveTo(e.getMatchNode());
                iReplacingCallback.insertHtml(html, useBuilderFormatting);
                return ReplaceAction.REPLACE;
            }
        };
        FindReplaceOptions findReplaceOptions = new FindReplaceOptions();
        findReplaceOptions.setReplacingCallback(callback);
        fileDoc.getRange().replace(compile, "", findReplaceOptions);
        saveWord(filePath, fileDoc);
    }


    /**
     * 书签替换图片
     *
     * @param filePath
     * @param imagePath
     * @param bookmarkName
     * @param width        宽度(建议200)
     * @param height       高度(建议100)
     * @throws Exception
     */
    public static void replaceBookmarkToImageFile(String filePath, String imagePath, String bookmarkName, double width, double height) throws Exception {
        if (StringUtils.isEmpty(filePath) || StringUtils.isEmpty(imagePath) || StringUtils.isEmpty(bookmarkName)) {
            throw new Exception("不符合约定!");
        }
        if (width < 1 || height < 1) {
            throw new Exception("不符合约定!");
        }
        Document doc = new Document(filePath);
        DocumentBuilder builder = new DocumentBuilder(doc);
        Shape shape = builder.insertImage(imagePath);
        shape.setWidth(width);
        shape.setHeight(height);
        shape.setWrapType(WrapType.NONE);
        builder.moveToBookmark(bookmarkName);
        builder.insertNode(shape);
        doc.save(filePath);
    }

    /**
     * 书签替换为文件
     *
     * @param filePath 被替换文件路径
     * @param map      key为书签名称 value为附件路径
     */
    public static void replaceBookmarkToFile(String filePath, Map<String, String> map) throws Exception {
        if (StringUtils.isBlank(filePath))
            throw new Exception("error: empty file path");
        if (map == null || map.isEmpty())
            throw new Exception("error: empty map");
        Document doc = new Document(filePath);
        DocumentBuilder builder = new DocumentBuilder(doc);
        for (Map.Entry<String, String> stringStringEntry : map.entrySet()) {
            builder.moveToBookmark(stringStringEntry.getKey());
            Document document = new Document(stringStringEntry.getValue());
            builder.insertDocument(document, ImportFormatMode.KEEP_DIFFERENT_STYLES);
        }
        doc.save(filePath);
    }

    /**
     * 转义正则特殊字符 （$()*+.[]?\^{},|）
     *
     * @param keyword
     * @return
     */
    public static String escapeExprSpecialWord(String keyword) {
        if (StringUtils.isNotBlank(keyword)) {
            String[] fbsArr = {"\\", "$", "(", ")", "*", "+", ".", "[", "]", "?", "^", "{", "}", "|"};
            for (String key : fbsArr) {
                if (keyword.contains(key)) {
                    keyword = keyword.replace(key, "\\" + key);
                }
            }
        }
        return keyword;
    }


    public static void writeWordTitle(DocumentBuilder builder, LinkedList<String> titles) throws Exception {
        if (CollUtil.isNotEmpty(titles)) {
            for (String title : titles) {
                try {
                    builder.insertCell();
                    if (title == null) {
                        title = "";
                    }
                    builder.write(title);
                } catch (Exception e) {
                    logger.error(e.getMessage(), e);
                }
            }
            builder.endRow();
        }
    }

    public static void writeWordTitle(DocumentBuilder builder, LinkedList<Double> doubleLinkedList, LinkedList<String> linkedLists) throws Exception {
        if (CollUtil.isNotEmpty(linkedLists) && CollUtil.isNotEmpty(doubleLinkedList)) {
            if (linkedLists.size() != doubleLinkedList.size()) {
                return;
            }
            for (int i = 0; i < linkedLists.size(); i++) {
                Cell cell = builder.insertCell();
                cell.getCellFormat().setWidth(doubleLinkedList.get(i).doubleValue());
                String s = linkedLists.get(i);
                if (s == null) {
                    s = "";
                }
                builder.write(s);
            }
            builder.endRow();
        }
    }

    public static Cell insertCell(String name, DocumentBuilder builder) throws Exception {
        Cell insertCell = builder.insertCell();
        builder.write(name);
        return insertCell;
    }

    public static void insertCell(DocumentBuilder builder, LinkedList<String> titles) throws Exception {
        if (CollUtil.isNotEmpty(titles)) {
            for (String title : titles) {
                builder.insertCell();
                builder.write(title);
            }
        }
    }


    /**
     * 保存word
     *
     * @param path
     * @throws Exception
     */
    public static void saveWord(String path, Document document) throws Exception {
        document.save(path) ;
//        File file = new File(path);
//        String fileName = file.getName();
//        String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
//        int[] arr = new int[]{SaveFormat.DOC, SaveFormat.DOT, SaveFormat.DOTX, SaveFormat.DOCM, SaveFormat.DOTX, SaveFormat.DOTM, SaveFormat.HTML};
//        for (int i = 0; i < arr.length; i++) {
//            if (StringUtils.equalsIgnoreCase(SaveFormat.getName(arr[i]), suffix)) {
//                document.save(path, arr[i]);
//                break;
//            }
//        }
    }

    public static void setDefaultTable(DocumentBuilder documentBuilder) {
        //设置具体宽度自动适应
        PreferredWidth preferredWidth = PreferredWidth.AUTO;
        documentBuilder.getParagraphFormat().setAlignment(ParagraphAlignment.CENTER);
        documentBuilder.getCellFormat().setPreferredWidth(preferredWidth);
        documentBuilder.getCellFormat().setVerticalMerge(CellVerticalAlignment.CENTER);
        documentBuilder.getCellFormat().setVerticalAlignment(CellVerticalAlignment.CENTER);
        documentBuilder.getCellFormat().setHorizontalMerge(CellVerticalAlignment.CENTER);
        documentBuilder.getCellFormat().setTopPadding(0);
        documentBuilder.getCellFormat().setBottomPadding(0);
        documentBuilder.getCellFormat().setLeftPadding(0);
        documentBuilder.getCellFormat().setRightPadding(0);
    }


    /**
     * 替换为标准格式
     *
     * @param str
     * @param removeTag       是否移除html标签
     * @param containFullStop 末尾是否包含句号
     * @return
     */
    public static String trim(String str, Boolean removeTag, Boolean containFullStop) {
        if (StringUtils.isBlank(str)) return str;
        if (removeTag) {
            str = StringUtils.strip(str.replaceAll("^<[^>]+>|<[^>]+>$", ""), "。");
            str += "。";
        }

        str = str.replaceAll(",", "，").replaceAll(";", "；")
                .replaceAll(",+", ",").replaceAll(";+", ";")
                .replaceAll("，+", "，").replaceAll("、+", "、")
                .replaceAll("。+", "。").replaceAll("；+", "；")
                .replaceAll("，\\s+。", "。").replaceAll("；\\s。", "。")
                .replaceAll("^[，|,|，|、|;|；|.|。]+", "");

        str = str.replaceAll("，；", "；").replaceAll("；，", "，")
                .replaceAll("，。", "。").replaceAll("。，", "，")
                .replaceAll("；。", "。").replaceAll("。；", "；");

        str = str.replaceAll("[，|,|，|、|;|；|.|。]+$", "");
        if (containFullStop) {
            str = str + "。";
        }
        return str;
    }

    public static void insertHtml(DocumentBuilder builder, String html, boolean useBuilderFormatting) {
        try {
            builder.insertHtml(html, useBuilderFormatting);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void insertHtml(DocumentBuilder builder, String html) throws Exception {
        builder.insertHtml(html);
    }


    /**
     * 合并单元格
     *
     * @param sourcePath      原文件路径
     * @param destinationPath 生成文件路径
     * @param skipIndex       标题行跳过序号
     * @param skipList        标题行调整名称
     * @throws Exception
     */
    public static void mergeTableCell(String sourcePath, String destinationPath, List<Integer> skipIndex, List<String> skipList) throws Exception {
        if (skipIndex == null) {
            skipIndex = Lists.newArrayList();
        }
        if (skipList == null) {
            skipList = Lists.newArrayList();
        }
        Document document = new Document(sourcePath);
        NodeCollection<Table> tables = document.getChildNodes(NodeType.TABLE, true);
        for (Table table : tables) {
            int rowCount = table.getRows().getCount();
            if (rowCount > 0) {
                CellCollection cells = table.getFirstRow().getCells();
                for (int i = 0; i < cells.getCount(); i++) {
                    if (skipIndex.contains(i)) {
                        continue;
                    }
                    Cell firstRowCell = cells.get(i);
                    if (skipList.contains(firstRowCell.getText().replace("\u0007", ""))) {
                        continue;
                    }
                    firstRowCell.getCellFormat().setVerticalMerge(CellMerge.FIRST);
                    for (int j = 1; j < rowCount; j++) {
                        Cell currentCell = table.getRows().get(j).getCells().get(i);
                        Cell prevCell = table.getRows().get(j - 1).getCells().get(i);
                        String text = currentCell.getText().replace("\u0007", "");
                        if (StringUtils.isBlank(text)) {
                            continue;
                        }
                        if (StringUtils.equalsIgnoreCase(currentCell.getText(), prevCell.getText())) {
                            currentCell.getCellFormat().setVerticalMerge(CellMerge.PREVIOUS);
                        } else {
                            currentCell.getCellFormat().setVerticalMerge(CellMerge.FIRST);
                        }
                    }
                }
            }
        }
        document.save(destinationPath);
    }


}
