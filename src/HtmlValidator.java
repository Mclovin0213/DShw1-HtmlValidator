/**
 * Add your own comments
 */

import java.util.*;


public class HtmlValidator {
    Queue<HtmlTag> tags;

    // Constructs an HtmlValidator, and initializes an empty queue of HtmlTags
    public HtmlValidator () {
        tags = new LinkedList<>();
    }

    // Constructs an HtmlValidator, and initializes the queue with a
    // given queue of HtmlTags
    public HtmlValidator(Queue<HtmlTag> tags) {
        if (tags == null) {
            throw new IllegalArgumentException();
        }
        this.tags = new LinkedList<>(tags);
    }

    // Adds the given tag to the HtmlValidators queue of tags
    public void addTag(HtmlTag tag) {
        tags.add(tag);
    }

    // Returns all tags stored in the queue
    public Queue<HtmlTag> getTags() {
        return tags;
    }

    // Removes all tags from the queue that are equal to the given element
    public void removeAll(String element) {
        if (element == null) {
            throw new IllegalArgumentException();
        } else {
            HtmlTag e = HtmlTag.parse(element);
            while (tags.contains(e)) {
                tags.remove(e);
            }
        }
    }

    // Goes through the queue of tags validating each HtmlTag, printing out the tags in the correct
    // way while also displaying the errors
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

    // Adds an indent to validate() method
    private static void addIndent(int count) {
        for (int i = 0; i < count; i++) {
            System.out.print("    ");
        }
    }
}
