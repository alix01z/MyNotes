package com.alix01z.noteappkotlin_mvvm.room.entities

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "table_note")
data class NoteEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo("note_id")
    val id:Int,

    @ColumnInfo("note_title")
    var title: String?,

    @ColumnInfo("note_content")
    var content: String?,

    @ColumnInfo("note_color")
    var color: String?,

    @ColumnInfo("note_pinned")
    var pinned:Boolean
):Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readByte() != 0.toByte()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(title)
        parcel.writeString(content)
        parcel.writeString(color)
        parcel.writeByte(if (pinned) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<NoteEntity> {
        override fun createFromParcel(parcel: Parcel): NoteEntity {
            return NoteEntity(parcel)
        }

        override fun newArray(size: Int): Array<NoteEntity?> {
            return arrayOfNulls(size)
        }
    }
}
