package misuy.mreader.entities

import android.util.Log
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import com.kursx.parser.fb2.Element
import com.kursx.parser.fb2.FictionBook
import com.kursx.parser.fb2.Section
import java.util.ArrayList
import kotlin.math.floor

class LinesList {
    var fontSize: TextUnit
    var lines: List<Line>

    constructor (bookData: FictionBook, fontSize: TextUnit, symbolWidth: Int, containerWidth: Int) {
        this.fontSize = fontSize
        lines = getLinesListFromSection(bookData.body.sections, symbolWidth, containerWidth)
    }

    private fun getLinesListFromSection(sections: ArrayList<Section>, symbolWidth: Int, containerWidth: Int): List<Line> {
        val lines: MutableList<Line> = mutableListOf()
        for (section in sections) {
            if (section.sections.size > 0) {
                lines.addAll(getLinesListFromSection(section.sections, symbolWidth, containerWidth))
            }
            else {
                for (element in section.elements) {
                    lines.addAll(this.getLinesFromElement(element.text, symbolWidth, containerWidth))
                }
            }
        }
        return(lines)
    }

    private fun getLinesFromElement(element: String, symbolWidth: Int, containerWidth: Int): List<Line> {
        val lines: MutableList<Line> = mutableListOf()
        val symbolsCount: Int = containerWidth / symbolWidth
        var startPos = 0
        while (true) {
            if (startPos < element.length)
            {
                if (element[startPos] == ' ') startPos++
                val endPos = startPos + symbolsCount - 1
                if (endPos < (element.length - 1)) {
                    if (element[endPos].isLetterOrDigit()) {
                        if (element[endPos-1].isLetterOrDigit()) lines.add(Line(element.substring(startPos, endPos) + '-'))
                        else lines.add(Line(element.substring(startPos, endPos)))
                        startPos = endPos
                    }
                    else {
                        lines.add(Line(element.substring(startPos, endPos+1)))
                        startPos = endPos + 1
                    }
                }
                else {
                    if (startPos != (element.length - 1)) lines.add(Line(element.substring(startPos)))
                    break
                }
            }
            else break
        }
        return(lines)
    }
}