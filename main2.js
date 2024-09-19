function xeploai() {
    var diem = parseInt(prompt("Nhập điểm:"));

    if (diem >= 0 && diem <= 10) {
    if (diem >= 9) {
        console.log("Xếp loại: Giỏi");
    } else if (diem >= 7) {
        console.log("Xếp loại: Khá");
    } else if (diem >= 5) {
        console.log("Xếp loại: Trung bình");
    } else {
        console.log("Xếp loại: Yếu");
    }
    } else {
    console.log("Điểm nhập vào không hợp lệ");
    }
}

function tichle() {
    let tich = 1;
    for (let i = 1; i <= 300; ++1) {
       if(i % 2 != 0){
          let *= i ;
       }
    }
    console.log("Tích các số lẻ từ 1 đến 300:", tich);
}

function tinhmu() {
    const x = parseFloat(prompt("Nhập giá trị x:"));
    const n = parseInt(prompt("Nhập giá trị n:"));
    const ketQua = Math.pow(x, n);
    console.log(`${x} ^ ${n} = ${ketQua}`);
}

xeploai();
console.log('----------------------------');
tichle();
console.log('----------------------------');
tinhmu();
console.log('----------------------------');


// Hàm map() sử dụng và hiểu về callback
Array.prototype.map2 = function(callback) {
    var arrlength = this.length , output = [] ;
    for(var i = 0 ; i < arrlength ; i++){
        var result = callback(this[i]);
        output.push(result);
    }
    return output 
};

var courses = [
    'Javascript',
    'PHP',
    'Ruby'
];

var htmls = courses.map2(function(course) {
    return `<h2>${course}</h2>` ;
});

console.log(htmls.join(''));


/// =========================

// hàm forEach() sử dụng callback
Array.prototype.forEach2 = function(callback) {
    for(var index in this) {
   // hasOwnProperty sẽ kiểm tra các phần tử chỉ nằm trong mảng chứ không nằm trong 
   // prototype
        if(this.hasOwnProperty(index)){
            callback(this[index] , index , this)
        }
    }
}

var courses = [
    'Javascrpit',
    'PHP',
    'C++'
];

courses.forEach2(function(course , index , arr) {
        console.log(course , index , arr);
});


//hàm filter() sử dụng callback
Array.prototype.filter2 = function(callback) {
    var output = [] ;
    for(var index in this) {
        if(this.hasOwnProperty(index)){
           var result = callback(this[index] , index , this);
           if(result) {
             output.push(this[index]);
           }
        }
    }
    return output ;
};


var courses = [
    {
        name: 'Javascript',
        coin: 100 
    },
    {
        name: 'PHP',
        coin: 340 
    },
    {
        name: 'Ruby',
        coin: 400 
    },
];

var filterArray = courses.filter2(function(course , index , arr) {
    return course.coin > 200 ;
});

console.log(filterArray);

//Hàm some() sử dụng callback
Array.prototype.some2 = function(callback) {
    for(var index in this) {
        if(this.hasOwnProperty(index)){
            if(callback(this[index] , index , this)){
                return true;
            } 
        }
    }
    return false ;
};

var courses = [
    {
        name: 'Javascript',
        coin: 100,
        isFinish: true,
    },
    {
        name: 'PHP',
        coin: 340,
        isFinish: false,
    },
    {
        name: 'Ruby',
        coin: 400,
        isFinish: false,
    },
];

var result = courses.some2(function (course) {
    return course.isFinish ;
});

console.log(result) ;

//sử dụng vídụ trên 
// hàm every() sử dụng callback

Array.prototype.every2 = function(callback) {
    var output = true ;
    for(var index in this) {
        if(this.hasOwnProperty(index)){
            var reslut = callback(this[index] , index , this);
            if(!reslut) {
                output = false;
                break;
            }
        }
    }
    return output ;
};

// hàm reduce() sử dụng callback
Array.prototype.reduce2 = function(callback , result){
    var arrlength = this.length ;
    let i = 0 ;
    if(arguments.length < 2 ){
        i = 1 ;
        result = this[0] ;
    }
    for(; i < arrlength ; ++i ){
        result = callback(this[i] , i , this);
    }
    return result ;
};


var arr = [1,2,3,4,5]