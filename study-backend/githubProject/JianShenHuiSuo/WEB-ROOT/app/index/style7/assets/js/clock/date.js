var monthNames = ["1��", "2��", "3��", "4��", "5��", "6��",
    "7��", "8��", "9��", "10��", "11��", "12��"
];
var dayNames = ["����", "��һ", "�ܶ�", "����", "����", "����", "���� "]

var newDate = new Date();
newDate.setDate(newDate.getDate() );
$('#Date1').html(  newDate.getFullYear() + "��" +monthNames[newDate.getMonth()] + newDate.getDate() + "�� " + dayNames[newDate.getDay()]   );


