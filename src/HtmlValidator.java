/**
 * Add your own comments
 */

import java.util.*;


public class HtmlValidator {
    Queue<HtmlTag> tags;

    public HtmlValidator () {
        tags = new LinkedList<>();
    }

    public HtmlValidator(Queue<HtmlTag> tags) {
        if (tags == null) {
            throw new IllegalArgumentException();
        }
        this.tags = new LinkedList<>(tags);
    }

    public void addTag(HtmlTag tag) {
        tags.add(tag);
    }

    public Queue<HtmlTag> getTags() {
        return tags;
    }

    public void removeAll(String element) {
        if (element == null) {
            throw new IllegalArgumentException();
        } else {
            for (HtmlTag tag: tags) {
                if (element.equals(tag.getElement())) {
                    tags.remove(tag);
                }
            }
        }
    }

    public void validate() {
        Stack<HtmlTag> temp = new Stack<>();
        int indentCount = 0;
        for (HtmlTag tag: tags) {
            if (tag.isSelfClosing() && tag.isOpenTag()) {
                addIndent(indentCount);
                System.out.println(tag);
            } else if (tag.isOpenTag()) {
                addIndent(indentCount);
                System.out.println(tag);
                temp.push(tag);
                indentCount++;
            } else {
                if (!temp.isEmpty() && (Objects.equals(temp.peek().getElement(), tag.getElement()))) {
                    indentCount--;
                    addIndent(indentCount);
                    System.out.println(tag);
                    temp.pop();
                } else {
                    System.out.println("ERROR unexpected tag: " + tag);
                }
            }
        }

        if (!temp.isEmpty()) {
            int size = temp.size();
            for (int i = 0; i < size; i++) {
                HtmlTag extraTag = temp.pop();
                System.out.println("ERROR unclosed tag: " + extraTag);
            }
        }
    }

    private static void addIndent(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print("    ");
        }
    }
}
